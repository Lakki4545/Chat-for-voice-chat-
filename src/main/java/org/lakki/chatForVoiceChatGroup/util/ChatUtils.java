package org.lakki.chatForVoiceChatGroup.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.lakki.chatForVoiceChatGroup.VoiceGroupChatPlugin;


public class ChatUtils {

    static MiniMessage mm = MiniMessage.miniMessage();


    private static final String PREFIX = VoiceGroupChatPlugin.getInstance().getConfig().getString("prefix");


    public static Component send(Player player, String msg) {
        String rawMessage;
        rawMessage = msg;
        rawMessage =rawMessage.replace("{pluginprefix}", PREFIX);
        rawMessage = PlaceholderUtil.parse(player, rawMessage);

        Component message = mm.deserialize(rawMessage);

        return message;
    }

    public static Component sendMessage(Player player, String msg, String groupName){
        String rawMessage;
        rawMessage = VoiceGroupChatPlugin.getInstance().getConfig().getString("message-style");
        rawMessage = rawMessage.replace("{pluginprefix}", PREFIX)
                .replace("{player}", player.getName())
                .replace("{message}",msg)
                .replace("{groupName}", groupName);
        rawMessage = PlaceholderUtil.parse(player, rawMessage);

        Component message = mm.deserialize(rawMessage);

        return message;
    }
}
