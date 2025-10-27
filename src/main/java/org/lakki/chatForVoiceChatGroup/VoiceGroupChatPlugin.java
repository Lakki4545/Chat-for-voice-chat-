package org.lakki.chatForVoiceChatGroup;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import org.lakki.chatForVoiceChatGroup.Api.VoiceChatProvider;
import org.lakki.chatForVoiceChatGroup.command.CommandManager;
import org.lakki.chatForVoiceChatGroup.manager.LangManager;

public final class VoiceGroupChatPlugin extends JavaPlugin {

    private LangManager lang;
    private static VoiceGroupChatPlugin instance;
    private VoiceChatProvider voiceChatProvider;

    @Override
    public void onEnable() {
        this.lang = new LangManager(this);
        saveDefaultConfig();
        instance = this;

        getLogger().info(lang.get("console.voiceChat.enabling"));

        this.voiceChatProvider = new VoiceChatProvider(this);

        new CommandManager(lang, this, voiceChatProvider).registerCommands();

        getLogger().info(lang.get("console.voiceChat.enable"));
    }

    @Override
    public void onDisable() {
        getLogger().info("VoiceGroupChat disabled.");
    }

    public static VoiceGroupChatPlugin getInstance() {
        return instance;
    }
}