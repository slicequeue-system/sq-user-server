package app.slicequeue.sq_user.user.command.domain;

import app.slicequeue.common.base.time_entity.BaseTimeSoftDeletedAtEntity;
import app.slicequeue.sq_user.common.converter.MapToJsonConverter;
import app.slicequeue.sq_user.user.command.domain.dto.CreateUserCommand;
import app.slicequeue.sq_user.user.command.domain.dto.UpdateUserCommand;
import app.slicequeue.sq_user.user.command.domain.type.UserState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@Getter
@Table(name = "users", indexes = {@Index(name = "idx_project_id_state_login_id", columnList = "project_id,state,login_id")})
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeSoftDeletedAtEntity {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;

    @NotNull(message = "projectId must not be null.")
    @Column(nullable = false)
    private Long projectId;

    @NotNull(message = "state must not be null.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserState state;

    @NotBlank(message = "loginId must not be blank.")
    @Column(nullable = false, length = 255)
    private String loginId;

    @NotNull(message = "pwd must not be null.")
    @Column(nullable = false, length = 255)
    private String pwd;

    @NotBlank(message = "nickname must not be blank.")
    @Column(nullable = false, length = 128)
    private String nickname;

    @Convert(converter = MapToJsonConverter.class)
    @Column(name = "profile_json") // H2 호환 위해 columnDefinition 생략
    private Map<String, Object> profile;

    public static User create(Long projectId,
                              UserState state,
                              String loginId,
                              String pwd,
                              String nickname,
                              Map<String, Object> profile) {
        User user = new User();
        user.userId = UserId.generateId();
        user.projectId = projectId;
        user.state = state;
        user.loginId = loginId;
        user.pwd = pwd;
        user.nickname = nickname;
        user.profile = profile;
        return user;
    }

    public static User create(CreateUserCommand command, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.userId = UserId.generateId();
        user.projectId = command.projectId();
        user.state = stateOrDefault(command.state());
        user.loginId = command.loginId();
        user.pwd = command.pwd();
        user.encodePwd(passwordEncoder);
        user.nickname = command.nickname();
        user.profile = command.profile();
        return user;
    }

    private static UserState stateOrDefault(UserState userState) {
        return userState != null ? userState : UserState.ACTIVE;
    }

    private void encodePwd(PasswordEncoder passwordEncoder) {
        this.pwd = passwordEncoder.encode(this.pwd);
    }

    public boolean matchPassword(String rawPwd, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPwd, this.pwd);
    }

    public void update(UpdateUserCommand command, PasswordEncoder passwordEncoder) {
        userId = UserId.generateId();
        projectId = command.projectId();
        state = stateOrDefault(command.state());
        loginId = command.loginId();
        pwd = command.pwd();
        encodePwd(passwordEncoder);
        nickname = command.nickname();
        profile = command.profile();
    }

    private void encodePwd(PasswordEncoder passwordEncoder) {
        this.pwd = passwordEncoder.encode(this.pwd);
    }

    public boolean matchPassword(String rawPwd, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPwd, this.pwd);
    }
}
