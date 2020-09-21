package me.kingtux.tuxcore;

import me.kingtux.tuxorm.BasicLoggingObject;
import me.kingtux.tuxorm.annotations.DBTable;
import me.kingtux.tuxorm.annotations.TableColumn;

@DBTable
public class VerifyKey extends BasicLoggingObject {
    @TableColumn(name = "user")
    private MCDUser mcdUser;
    @TableColumn
    private String key;

    public VerifyKey(MCDUser mcdUser, String key) {
        this.mcdUser = mcdUser;
        this.key = key;
    }

    public VerifyKey() {

    }

    public MCDUser getMcdUser() {
        return mcdUser;
    }

    public String getKey() {
        return key;
    }
}
