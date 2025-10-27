package org.lakki.chatForVoiceChatGroup.command;

import org.bukkit.command.PluginCommand;
import org.lakki.chatForVoiceChatGroup.Api.VoiceChatProvider;
import org.lakki.chatForVoiceChatGroup.VoiceGroupChatPlugin;
import org.lakki.chatForVoiceChatGroup.manager.LangManager;

public class CommandManager {

    private LangManager langManager;
    private final VoiceGroupChatPlugin plugin;
    private final VoiceChatProvider voiceChatProvider;

    public CommandManager(LangManager langManager, VoiceGroupChatPlugin plugin, VoiceChatProvider provider) {
        this.langManager = langManager;
        this.plugin = plugin;
        this.voiceChatProvider = provider;
    }

    public void registerCommands() {
        PluginCommand cmd = plugin.getCommand("groupchat");
        if (cmd != null) {
            var executor = new GroupChatCommand(langManager, plugin, voiceChatProvider);
            cmd.setExecutor(executor);
            cmd.setTabCompleter(executor);
        }
    }
}