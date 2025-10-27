package org.lakki.chatForVoiceChatGroup.command;

import net.kyori.adventure.text.ComponentLike;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.lakki.chatForVoiceChatGroup.Api.VoiceChatProvider;
import org.lakki.chatForVoiceChatGroup.VoiceGroupChatPlugin;
import org.lakki.chatForVoiceChatGroup.manager.LangManager;
import org.lakki.chatForVoiceChatGroup.service.GroupMessageService;
import org.lakki.chatForVoiceChatGroup.util.ChatUtils;

import java.util.Collections;
import java.util.List;

public class GroupChatCommand implements CommandExecutor, TabCompleter {

    private LangManager langManager;
    private final GroupMessageService messageService;

    public GroupChatCommand(LangManager langManager, org.bukkit.plugin.java.JavaPlugin plugin, VoiceChatProvider provider) {
        this.langManager = langManager;
        this.messageService = new GroupMessageService(langManager, plugin, provider);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatUtils.send((Player) sender, langManager.get("command.groupchat.player-error") ));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatUtils.send(player, langManager.get("command.groupchat.usage")));
            return true;
        }

        String message = String.join(" ", args);
        messageService.sendGroupMessage(player, message);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}