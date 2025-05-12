package app.slicequeue.sq_user.user.command.application.usecase;

import app.slicequeue.sq_user.common.event.EventType;
import app.slicequeue.sq_user.common.event.payload.UserInfoChangedEventPayload;
import app.slicequeue.sq_user.common.outbox_relay.OutboxEventPublisher;
import app.slicequeue.sq_user.user.command.application.service.UpdateUserService;
import app.slicequeue.sq_user.user.command.domain.User;
import app.slicequeue.sq_user.user.command.domain.UserId;
import app.slicequeue.sq_user.user.command.domain.dto.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {

    private final UpdateUserService updateUserService;
    private final OutboxEventPublisher outboxEventPublisher;

    @Transactional
    public UserId execute(UpdateUserCommand command) {
        // 사용자 정보 수정
        User user = updateUserService.updateUser(command);
        // 이벤트 발행
        outboxEventPublisher.publish(
                EventType.USER_INFO_CHANGED,
                UserInfoChangedEventPayload.builder()
                        .userId(user.getUserId().getId())
                        .projectId(user.getProjectId())
                        .state(user.getState())
                        .loginId(user.getLoginId())
                        .nickname(user.getNickname())
                        .profile(user.getProfile())
                        .build(),
                user.getProjectId());
        return user.getUserId();
    }

}
