package me.kingtux.tuxcore;

import org.bukkit.plugin.java.JavaPlugin;

public final class TuxCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadListeners();
        saveDefaultConfig();
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadListeners() {
    }
}
