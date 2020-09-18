package me.kingtux.tuxcore.discord;

import me.kingtux.tuxcore.MCDUser;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.VerifyKey;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter {
    private JDA jda;
    private final TuxCore tuxCore;
    private Role role;

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
            jda = new JDABuilder(tuxCore.getConfig().getString("discord.token")).addEventListeners(this).build();
        } catch (LoginException e) {
            tuxCore.getSLF4JLogger().error("Unable to load Discord", e);
        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        try {
            role = event.getJDA().getRoleById(tuxCore.getConfig().getString("discord.discord-role"));
        } catch (NullPointerException e) {
            tuxCore.getLogger().warning("Unable to access Discord Role Info");
        }
    }

    public void close() {
        jda.shutdown();
    }
}
