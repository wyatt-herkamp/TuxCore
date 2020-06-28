package me.kingtux.tuxcore.discord;

import me.kingtux.tuxcore.MCDUser;
import me.kingtux.tuxcore.TuxCore;
import me.kingtux.tuxcore.VerifyKey;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

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
    }

    public void close() {
        jda.shutdown();
    }
}
