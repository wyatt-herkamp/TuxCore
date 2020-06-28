package me.kingtux.tuxcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitCrap {
    public static Player getOnlinePlayerByName(String s) {
       return Bukkit.getOnlinePlayers().stream().filter(o -> o.getDisplayName().equalsIgnoreCase(s)).findFirst().orElse(null);
    }
}
