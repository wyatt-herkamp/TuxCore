package me.kingtux.tuxcore;

import dev.nitrocommand.bukkit.BukkitCommandCore;
import me.kingtux.tuxcore.discord.DiscordBot;
import me.kingtux.tuxcore.listeners.ChatListener;
import me.kingtux.tuxorm.TOConnection;
import org.bukkit.plugin.java.JavaPlugin;

public final class TuxCore extends JavaPlugin {
    private TOConnection commonConnection;
    private BukkitCommandCore commandCore;
    private DiscordBot discordBot;


    @Override
    public void onEnable() {
        // Plugin startup logic
        loadListeners();
        saveDefaultConfig();
        loadConnection();
    }

    private void loadConnection() {
    }


    @Override
    public void onDisable() {
        if (commonConnection != null) commonConnection.close();
        if (discordBot != null) discordBot.close();
        // Plugin shutdown logic
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    public TOConnection getCommonConnection() {
        return commonConnection;
    }

    public BukkitCommandCore getCommandCore() {
        return commandCore;
    }
}
