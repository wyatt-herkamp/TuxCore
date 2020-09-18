package me.kingtux.tuxcore;

import me.kingtux.tuxorm.BasicLoggingObject;
import me.kingtux.tuxorm.annotations.DBTable;
import me.kingtux.tuxorm.annotations.TableColumn;
import net.dv8tion.jda.api.entities.User;

import java.util.UUID;

@DBTable
public class MCDUser extends BasicLoggingObject {
    @TableColumn(name = "mc_user")
    private UUID mcUserID;
    @TableColumn
    private User user;

    public MCDUser(User user) {
        this.user = user;
    }

    public MCDUser() {

    }

    public UUID getMcUserID() {
        return mcUserID;
    }

    public void setMcUserID(UUID mcUserID) {
        this.mcUserID = mcUserID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
