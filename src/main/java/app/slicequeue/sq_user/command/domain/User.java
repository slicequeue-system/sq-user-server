package app.slicequeue.sq_user.command.domain;

import app.slicequeue.common.base.time_entity.BaseTimeSoftDeletedAtEntity;
import app.slicequeue.sq_user.command.domain.type.UserState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Table(name = "users", indexes = {@Index(name = "idx_project_id_state_login_id", columnList = "project_id,state,login_id")})
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeSoftDeletedAtEntity {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;
    private Long projectId;
    @Enumerated(EnumType.STRING)
    private UserState state;
    private String loginId;
    private String pwd;
    private String nickname;
    // TODO profile_json
}
