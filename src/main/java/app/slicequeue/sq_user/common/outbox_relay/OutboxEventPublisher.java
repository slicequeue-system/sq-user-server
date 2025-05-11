package app.slicequeue.sq_user.common.outbox_relay;

import app.slicequeue.common.snowflake.Snowflake;
import app.slicequeue.sq_user.common.event.Event;
import app.slicequeue.sq_user.common.event.EventPayload;
import app.slicequeue.sq_user.common.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxEventPublisher {
    private final Snowflake outboxIdSnowflake = new Snowflake();
    private final Snowflake eventIdSnowflake = new Snowflake();
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(EventType type, EventPayload payload, Long shardKey) {
        Outbox outbox = Outbox.create(
                type,
                Event.of(eventIdSnowflake.nextId(), type, payload).toJson(),
                shardKey % MessageRelayConstants.SHARD_COUNT);
        applicationEventPublisher.publishEvent(OutboxEvent.of(outbox));
    }
}
