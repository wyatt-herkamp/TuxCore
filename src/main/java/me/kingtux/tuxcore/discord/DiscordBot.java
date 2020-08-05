package me.kingtux.tuxcore.discord;

import me.kingtux.tuxcore.MCDUser;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.VerifyKey;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private JDA jda;
    private TuxCore tuxCore;

    public DiscordBot(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
        loadBot();
        loadSQLObjects();
    }

    private void loadSQLObjects() {
        tuxCore.getCommonConnection().registerSecondarySerializer(User.class, new UserSerializer(jda, tuxCore.getCommonConnection()));
        tuxCore.getCommonConnection().registerClass(MCDUser.class);
        tuxCore.getCommonConnection().registerClass(VerifyKey.class);
    }

    private void loadBot() {
        try {
            jda = new JDABuilder(tuxCore.getConfig().getString("discord.token")).build();
        } catch (LoginException e) {
            tuxCore.getSLF4JLogger().error("Unable to load Discord", e);
        }
    }

    public void close() {
        jda.shutdown();
    }
}
