package me.kingtux;

import me.kingtux.tuxorm.annotations.TableColumn;
import net.dv8tion.jda.api.entities.User;

import java.util.UUID;

public class TuxCoreUser {
    @TableColumn(name = "mc_user")
    private UUID mcUserID;
    @TableColumn()
    private String cachedUserName;
    @TableColumn
    private String cachedDisplayName;
    @TableColumn
    private User discordUser;

    public TuxCoreUser(User user) {
        this.discordUser = user;
    }

    public TuxCoreUser() {

    }

    public UUID getMcUserID() {
        return mcUserID;
    }

    public void setMcUserID(UUID mcUserID) {
        this.mcUserID = mcUserID;
    }

    public User getUser() {
        return discordUser;
    }

    public void setUser(User user) {
        this.discordUser = user;
    }

    public String getCachedUserName() {
        return cachedUserName;
    }

    public void setCachedUserName(String cachedUserName) {
        this.cachedUserName = cachedUserName;
    }

    public String getCachedDisplayName() {
        return cachedDisplayName;
    }

    public void setCachedDisplayName(String cachedDisplayName) {
        this.cachedDisplayName = cachedDisplayName;
    }

    public User getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(User discordUser) {
        this.discordUser = discordUser;
    }
}
