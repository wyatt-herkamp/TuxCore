package me.kingtux.tuxcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.kingtux.tuxcore.TuxCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private TuxCore kingtuxskyblock;

    public ChatListener(TuxCore kingtuxskyblock) {
        this.kingtuxskyblock = kingtuxskyblock;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        if (kingtuxskyblock.getConfig().getBoolean("chat.tagcolor.enabled")) {
            String color = kingtuxskyblock.getConfig().getString("chat.tagcolor.color");

            for (String string : message.split(" ")) {

                if (Bukkit.getPlayerExact(string) != null) {
                    message = message.replace(string, String.format("&%s%s&r", color, string));
                }
            }
        }
        if (e.getPlayer().hasPermission("chat.color")) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        }
        e.setCancelled(true);
        String fullMessage = kingtuxskyblock.getConfig().getString("chat.format");
        fullMessage = fullMessage.replace("{message}", message);
        fullMessage = PlaceholderAPI.setPlaceholders(e.getPlayer(), fullMessage);
        String finalFullMessage = fullMessage;
        e.getRecipients().forEach(player -> player.sendMessage(finalFullMessage));

    }
}
