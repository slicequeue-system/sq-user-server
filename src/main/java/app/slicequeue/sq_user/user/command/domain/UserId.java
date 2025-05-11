package app.slicequeue.sq_user.user.command.domain;

import app.slicequeue.common.base.id_entity.BaseSnowflakeId;
import app.slicequeue.common.snowflake.Snowflake;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserId extends BaseSnowflakeId<UserId> {

    static Snowflake snowflake = new Snowflake();

    @Override
    protected Snowflake getSnowflake() {
        return snowflake;
    }

    public static UserId generateId() {
        return new UserId().generate();
    }

    public static UserId from(Long idValue) {
        return from(idValue, UserId.class);
    }
}
