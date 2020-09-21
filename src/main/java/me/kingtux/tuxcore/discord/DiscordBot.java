package me.kingtux.tuxcore.discord;

import dev.nitrocommand.jda4.JDA4CommandCore;
import me.kingtux.tuxcore.MCDUser;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.VerifyKey;
import me.kingtux.tuxcore.discord.commands.VerifyCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
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
    private final JDA4CommandCore commandCore;

    public DiscordBot(TuxCore tuxCore) {
        this.tuxCore = tuxCore;
        loadBot();
        loadSQLObjects();
        commandCore = new JDA4CommandCore(jda, "/");
        commandCore.registerCommand(new VerifyCommand(tuxCore));
    }

    private void loadSQLObjects() {
        tuxCore.getCommonConnection().registerSecondarySerializer(User.class, new UserSerializer(jda, tuxCore.getCommonConnection()));
        tuxCore.getCommonConnection().registerClass(MCDUser.class);
        tuxCore.getCommonConnection().registerClass(VerifyKey.class);
    }

    private void loadBot() {
        try {
            jda = new JDABuilder(tuxCore.getConfig().getString("bot.token")).addEventListeners(this).build();
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
        System.out.println("Invite Link: " + jda.getInviteUrl(Permission.values()));
    }

    public void close() {
        jda.shutdown();
    }
}
