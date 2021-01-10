package me.kingtux.tuxcore.settings;

import me.kingtux.tuxorm.BasicLoggingObject;
import me.kingtux.tuxorm.annotations.DBTable;
import me.kingtux.tuxorm.annotations.TableColumn;

import java.util.Objects;

@DBTable
public class Setting extends BasicLoggingObject {
    @TableColumn(name = "key")
    private String settingKey;
    @TableColumn
    private String settingValue;

    public Setting() {

    }

    public Setting(Settings settingKey, String settingValue) {
        this.settingKey = settingKey.name();
        this.settingValue = settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public Settings getSettingKey() {
        return Settings.valueOf(settingKey);
    }

    public String getSettingValue() {
        return settingValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Setting setting = (Setting) o;
        return Objects.equals(getSettingKey(), setting.getSettingKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSettingKey());
    }

    @Override
    public String toString() {
        return "Setting{" +
                "settingKey='" + settingKey + '\'' +
                ", settingValue='" + settingValue + '\'' +
                '}';
    }
}
