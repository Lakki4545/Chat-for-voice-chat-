package org.lakki.chatForVoiceChatGroup.Api;


import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class VoiceChatProvider implements VoicechatPlugin {

    private final JavaPlugin plugin;
    private VoicechatServerApi serverApi;

    public VoiceChatProvider(JavaPlugin plugin) {
        this.plugin = plugin;
        initializeApi();
    }

    private void initializeApi() {
        try {
            var reg = Bukkit.getServicesManager().getRegistration(VoicechatServerApi.class);
            if (reg != null) {
                serverApi = reg.getProvider();
                plugin.getLogger().info("VoiceChat API found via ServiceManager.");
            } else {
                var svc = Bukkit.getServicesManager().getRegistration(BukkitVoicechatService.class);
                if (svc != null) {
                    svc.getProvider().registerPlugin(this);
                    plugin.getLogger().info("Registered VoiceGroupChat as VoiceChatPlugin.");
                }
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to initialize VoiceChat API: " + e.getMessage());
        }
    }

    @Override
    public String getPluginId() {
        return "voicegroupchat";
    }

    @Override
    public void initialize(VoicechatApi api) {
        if (api instanceof VoicechatServerApi server) {
            this.serverApi = server;
            plugin.getLogger().info("VoiceChat API initialized successfully.");
        } else {
            plugin.getLogger().warning("VoiceChat API not compatible.");
        }
    }

    public VoicechatServerApi getServerApi() {
        return serverApi;
    }

    public boolean isAvailable() {
        return serverApi != null;
    }
}
