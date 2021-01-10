package me.kingtux.tuxcore.discord;

import dev.nitrocommand.jda4.JDA4CommandCore;
import dev.nitrocommand.jda4.JDAUtils;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.discord.commands.SettingCommand;
import me.kingtux.tuxcore.discord.commands.TuxAdmin;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class TuxCoreDiscord implements EventListener {
    private JDA jda;
    private JDA4CommandCore commandCore;
    private final TuxCore tuxCore;
    private int serverCountRunnableID;
    private ServerCountUpdater runnable;

    public TuxCoreDiscord(TuxCore tuxCore) throws LoginException {
        this.tuxCore = tuxCore;
        open();
        runnable = new ServerCountUpdater(tuxCore);
        //Every 5 minutes update
        serverCountRunnableID = Bukkit.getScheduler().runTaskTimerAsynchronously(tuxCore, runnable, 0, 6000).getTaskId();
    }

    public TuxCore getTuxCore() {
        return tuxCore;
    }

    public void reset() throws LoginException {
        close();
        open();
    }

    public void open() throws LoginException {
        ConfigurationSection bot = tuxCore.getConfig().getConfigurationSection("bot");
        jda = JDABuilder.createDefault(bot.getString("token")).addEventListeners(this).build();

        commandCore = new JDA4CommandCore(jda, bot.getString("prefix"));
        loadCommands();
    }

    private void loadCommands() {
        commandCore.registerCommand(new SettingCommand(tuxCore));
        commandCore.registerCommand(new TuxAdmin(tuxCore));

    }

    public void close() {
        if (jda == null) return;
        jda.shutdown();
        jda = null;
        commandCore = null;
    }

    public ServerCountUpdater getRunnable() {
        return runnable;
    }

    public JDA getJda() {
        return jda;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
    }
}
