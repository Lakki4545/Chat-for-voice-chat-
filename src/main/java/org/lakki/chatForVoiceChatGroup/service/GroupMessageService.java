package org.lakki.chatForVoiceChatGroup.service;


import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.lakki.chatForVoiceChatGroup.Api.VoiceChatProvider;
import org.lakki.chatForVoiceChatGroup.manager.LangManager;
import org.lakki.chatForVoiceChatGroup.util.ChatUtils;

import java.util.UUID;

public class GroupMessageService {

    private LangManager langManager;
    private final JavaPlugin plugin;
    private final VoiceChatProvider provider;

    public GroupMessageService(LangManager langManager, JavaPlugin plugin, VoiceChatProvider provider) {
        this.langManager = langManager;
        this.plugin = plugin;
        this.provider = provider;
    }

    public void sendGroupMessage(Player sender, String message) {
        if (!provider.isAvailable()) {
            sender.sendMessage(ChatUtils.send(sender, langManager.get("console.voiceChat.API.notAvailable")));
            return;
        }

        VoicechatConnection connection = provider.getServerApi().getConnectionOf(sender.getUniqueId());
        if (connection == null || connection.getGroup() == null) {
            sender.sendMessage(ChatUtils.send(sender, langManager.get("command.groupchat.nullGroup")));
            return;
        }

        Group group = connection.getGroup();
        UUID groupId = group.getId();

        int sent = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            VoicechatConnection conn = provider.getServerApi().getConnectionOf(p.getUniqueId());
            if (conn != null && conn.getGroup() != null && conn.getGroup().getId().equals(groupId)) {
                p.sendMessage(ChatUtils.sendMessage(sender,message, group.getName()));
                sent++;
            }
        }

    }
}