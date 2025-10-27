package org.lakki.chatForVoiceChatGroup.manager;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LangManager {

    private final JavaPlugin plugin;
    private FileConfiguration langConfig;

    public LangManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadLang();
    }

    private void loadLang() {
        File langFile = new File(plugin.getDataFolder(), "lang.yml");

        if (!langFile.exists()) {
            plugin.saveResource("lang.yml", false);
        }

        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public String get(String path) {
        String value = langConfig.getString(path);
        return value != null ? value : "§cMissing lang key: " + path;
    }

    public void reload() {
        loadLang();
    }
}