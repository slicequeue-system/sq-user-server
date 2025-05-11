package app.slicequeue.sq_user.user.command.domain;

import app.slicequeue.common.base.id_entity.BaseSnowflakeId;
import app.slicequeue.common.snowflake.Snowflake;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserId extends BaseSnowflakeId<UserId> {

    static Snowflake snowflake = new Snowflake();

    public UserId(Long id) {
        super(id);
    }

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

