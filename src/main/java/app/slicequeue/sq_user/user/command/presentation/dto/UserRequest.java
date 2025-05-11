package app.slicequeue.sq_user.user.command.presentation.dto;

import app.slicequeue.sq_user.common.util.StringPatternMatchingUtil.RegexValue;
import app.slicequeue.sq_user.user.command.domain.type.UserState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.Map;

@Getter
public class UserRequest {
    @NotNull(message = "프로젝트 식별값이 없습니다.")
    private Long projectId;
    private UserState state;
    @NotBlank(message = "로그인 아이디가 값이 비었습니다.")
    private String loginId;
    @NotBlank(message = "인증 비밀번호가 값이 비었습니다.")
    @Pattern(regexp = RegexValue.VALID_PASSWORD_LV3_REGEX, message =
            "형식이 유효하지 않습니다. " + RegexValue.VALID_PASSWORD_LV3_REGEX_DESCRIPTION)
    private String pwd;
    @NotBlank(message = "닉네임 값이 비었습니다.")
    private String nickname;
    private Map<String, Object> profile;

    public UserRequest(
            Long projectId,
            UserState state,
            String loginId,
            String pwd,
            String nickname,
            Map<String, Object> profile) {
        this.projectId = projectId;
        this.state = state;
        this.loginId = loginId;
        this.pwd = pwd;
        this.nickname = nickname;
        this.profile = profile;
    }

    public UserRequest() {
    }
}
