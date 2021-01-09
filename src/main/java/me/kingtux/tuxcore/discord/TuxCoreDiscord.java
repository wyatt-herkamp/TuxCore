package me.kingtux.tuxcore.discord;

import dev.nitrocommand.jda4.JDA4CommandCore;
import me.kingtux.tuxcore.TuxCore;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.configuration.ConfigurationSection;

import javax.security.auth.login.LoginException;

public class TuxCoreDiscord {
    private JDA jda;
    private JDA4CommandCore commandCore;
    private final TuxCore tuxCore;

    public TuxCoreDiscord(TuxCore tuxCore) throws LoginException {
        this.tuxCore = tuxCore;
        open();
    }

    public void reset() throws LoginException {
        close();
        open();
    }

    public void open() throws LoginException {
        ConfigurationSection bot = tuxCore.getConfig().getConfigurationSection("bot");
        jda = new JDABuilder(bot.getString("token")).addEventListeners(this).build();
        commandCore = new JDA4CommandCore(jda, bot.getString("prefix"));
    }

    public void close() {
        if (jda == null) return;
        jda.shutdown();
        jda = null;
        commandCore = null;
    }
}
