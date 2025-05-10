package app.slicequeue.sq_user.user.command.domain;

import app.slicequeue.common.base.time_entity.BaseTimeSoftDeletedAtEntity;
import app.slicequeue.sq_user.common.converter.MapToJsonConverter;
import app.slicequeue.sq_user.user.command.domain.type.UserState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(nullable = false)
    private Long projectId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserState state;

    @Column(nullable = false, length = 255)
    private String loginId;

    @Column(nullable = false, length = 255)
    private String pwd;

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
}
