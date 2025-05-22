package app.slicequeue.sq_user.common.event.payload;

import app.slicequeue.common.base.messagerelay.event.EventPayload;
import app.slicequeue.sq_user.user.command.domain.type.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoChangedEventPayload implements EventPayload {

    private long userId;
    private long projectId;
    private String loginId;
    private UserState state;
    private String nickname;
    private Map<String, Object> profile;
    private Instant createdAt;
    private Instant updatedAt;
}
