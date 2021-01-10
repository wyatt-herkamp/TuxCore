package me.kingtux.tuxcore;

import dev.nitrocommand.bukkit.BukkitCommandCore;
import me.bristermitten.pdm.PluginDependencyManager;
import me.kingtux.enumconfig.BukkitYamlHandler;
import me.kingtux.enumconfig.EnumConfigLoader;
import me.kingtux.lava.PropertiesUtils;
import me.kingtux.tuxcore.commands.TuxAdmin;
import me.kingtux.tuxcore.discord.TuxCoreDiscord;
import me.kingtux.tuxcore.listeners.ChatListener;
import me.kingtux.tuxcore.settings.SettingManager;
import me.kingtux.tuxjsql.core.TuxJSQLBuilder;
import me.kingtux.tuxorm.TOConnection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Map;
import java.util.Properties;

public final class TuxCore extends JavaPlugin {
    private TOConnection commonConnection;
    private BukkitCommandCore commandCore;
    private TuxCoreDiscord tuxCoreDiscord;
    private SettingManager settingManager;

    @Override
    public void onEnable() {
        PluginDependencyManager dependencyManager = PluginDependencyManager.of(this);
        dependencyManager.loadAllDependencies().thenRun(() -> {
            loadListeners();
            saveDefaultConfig();
            loadConnection();
            Logger.getRootLogger().setLevel(Level.DEBUG);
            loadCommands();
            loadDiscord();
            loadSetting();
            loadLang();
        });
    }

    public void loadLang() {
        MCLocale.loadLang(this);
    }

    private void loadSetting() {
        settingManager = new SettingManager(this);
    }

    private void loadDiscord() {
        try {
            tuxCoreDiscord = new TuxCoreDiscord(this);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }


    private void loadCommands() {
        commandCore = new BukkitCommandCore(this);
        commandCore.setMissingPermissionHandler((bukkitController, permission) -> bukkitController.getCommandSender().sendMessage(MCLocale.MISSING_PERMISSION.colorAndSubstitute(Map.of("permission", permission))));
        commandCore.setMustBeAPlayerHandler(bukkitController -> bukkitController.getCommandSender().sendMessage(MCLocale.MUST_BE_PLAYER.color()));
        commandCore.registerCommand(new TuxAdmin(this));
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

    public TuxCoreDiscord getTuxCoreDiscord() {
        return tuxCoreDiscord;
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

    public SettingManager getSettingManager() {
        return settingManager;
    }
}
