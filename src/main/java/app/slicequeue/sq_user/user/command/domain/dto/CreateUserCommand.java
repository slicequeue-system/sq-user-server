package app.slicequeue.sq_user.user.command.domain.dto;

import app.slicequeue.sq_user.user.command.domain.type.UserState;
import app.slicequeue.sq_user.user.command.presentation.dto.UserRequest;

import java.util.Map;

public record CreateUserCommand(Long projectId, UserState state, String loginId, String pwd, String nickname,
                                Map<String, Object> profile) {

    public static CreateUserCommand from(UserRequest request) {
        return new CreateUserCommand(request.getProjectId(), request.getState(), request.getLoginId(),
                request.getPwd(), request.getNickname(), request.getProfile());
    }
}
