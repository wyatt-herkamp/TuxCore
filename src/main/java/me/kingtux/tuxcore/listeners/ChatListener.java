package me.kingtux.tuxcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;

import me.kingtux.tuxcore.TuxCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;

public class ChatListener implements Listener {
    private TuxCore kingtuxskyblock;

    public ChatListener(TuxCore kingtuxskyblock) {
        this.kingtuxskyblock = kingtuxskyblock;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        sendMessage(message, e.getPlayer(), kingtuxskyblock, e.getRecipients());
        e.setCancelled(true);
    }

    public static void sendMessage(String message, OfflinePlayer player, TuxCore tuxCore, Collection<? extends Player> players) {
        if (tuxCore.getConfig().getBoolean("chat.tagcolor.enabled")) {
            String color = tuxCore.getConfig().getString("chat.tagcolor.color");

            for (String string : message.split(" ")) {

                if (Bukkit.getPlayerExact(string) != null) {
                    message = message.replace(string, String.format("&%s%s&r", color, string));
                }
            }
        }
        if (player.isOnline()) {
            if (player.getPlayer().hasPermission("chat.color")) {
                message = ChatColor.translateAlternateColorCodes('&', message);
            }
        }
        String fullMessage = tuxCore.getConfig().getString("chat.format");
        fullMessage = fullMessage.replace("{message}", message);
        fullMessage = PlaceholderAPI.setPlaceholders(player, fullMessage);
        String finalFullMessage = fullMessage;
        players.forEach(p -> p.sendMessage(finalFullMessage));

    }
}
