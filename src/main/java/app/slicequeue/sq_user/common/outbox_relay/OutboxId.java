package app.slicequeue.sq_user.common.outbox_relay;

import app.slicequeue.common.base.id_entity.BaseSnowflakeId;
import app.slicequeue.common.snowflake.Snowflake;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OutboxId extends BaseSnowflakeId<OutboxId> {

    static Snowflake snowflake = new Snowflake();

    public OutboxId(Long id) {
        super(id);
    }

    @Override
    protected Snowflake getSnowflake() {
        return snowflake;
    }

    public static OutboxId generateId() {
        return new OutboxId().generate();
    }

    public static OutboxId from(Long idValue) {
        return from(idValue, OutboxId.class);
    }
}

