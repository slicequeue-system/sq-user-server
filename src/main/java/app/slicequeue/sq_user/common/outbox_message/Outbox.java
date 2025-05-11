package app.slicequeue.sq_user.common.outbox_message;

import app.slicequeue.sq_user.common.converter.MapToJsonConverter;
import app.slicequeue.sq_user.common.event.EventType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Table(name = "outbox")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Outbox {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "outbox_id"))
    private OutboxId outboxId;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Convert(converter = MapToJsonConverter.class)
    @Column(name = "payload_json")
    private Map<String, Object> payload;
    private Long targetId;
    private Instant createdAt;

}
