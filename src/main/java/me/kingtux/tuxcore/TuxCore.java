package me.kingtux.tuxcore;

import me.bristermitten.pdm.PDMBuilder;
import me.bristermitten.pdm.PluginDependencyManager;
import me.kingtux.lava.PropertiesUtils;
import me.kingtux.tuxcore.listeners.ChatListener;
import me.kingtux.tuxjsql.core.TuxJSQLBuilder;
import me.kingtux.tuxorm.TOConnection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Properties;

public final class TuxCore extends JavaPlugin {
    private TOConnection commonConnection;

    @Override
    public void onEnable() {
        PluginDependencyManager dependencyManager = new PDMBuilder(this).build();
        dependencyManager.loadAllDependencies().thenRun(() -> {
            loadListeners();
            saveDefaultConfig();
            loadConnection();
            Logger.getRootLogger().setLevel(Level.DEBUG);

        });
    }

    private void loadConnection() {
        saveResource("db.properties", false);
        try {
            Properties properties = PropertiesUtils.loadPropertiesFromFile(new File(getDataFolder(), "db.properties"));
            commonConnection = new TOConnection(TuxJSQLBuilder.create(properties));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        if (commonConnection != null) commonConnection.close();
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    public TOConnection getCommonConnection() {
        return commonConnection;
    }

}
