package me.kingtux.tuxcore.discord;

import dev.tuxjsql.basic.sql.BasicDataTypes;
import dev.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxorm.TOConnection;
import me.kingtux.tuxorm.serializers.SingleSecondarySerializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class UserSerializer implements SingleSecondarySerializer<User, String> {
    private JDA jda;
    private TOConnection toConnection;

    public UserSerializer(JDA jda, TOConnection toConnection) {
        this.jda = jda;
        this.toConnection = toConnection;
    }

    @Override
    public String getSimplifiedValue(User o) {
        return o.getId();
    }

    @Override
    public User buildFromSimplifiedValue(String value) {
        return jda.getUserById(value);
    }

    @Override
    public ColumnBuilder createColumn(String name) {
        return toConnection.getBuilder().createColumn().setDataType(BasicDataTypes.TEXT).name(name);
    }
}
