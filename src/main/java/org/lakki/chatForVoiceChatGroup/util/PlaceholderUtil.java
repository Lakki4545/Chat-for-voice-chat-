package org.lakki.chatForVoiceChatGroup.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderUtil {
    /**
     * Обрабатывает плейсхолдеры PlaceholderAPI в тексте.
     *
     * @param player игрок, для которого нужно применить плейсхолдеры
     * @param text   строка, в которой нужно заменить плейсхолдеры
     * @return готовый текст с подставленными значениями
     */
    public static String parse(Player player, String text) {
        if (text == null) return "";
        if (player == null) return text;

        // Проверяем, установлен ли PlaceholderAPI
        if (isPapiEnabled()) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }

        return text;
    }

    /**
     * @return true, если PlaceholderAPI найден
     */
    public static boolean isPapiEnabled() {
        return org.bukkit.Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }
}

