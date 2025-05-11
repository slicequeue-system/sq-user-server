package app.slicequeue.sq_user.user.command.domain;

import app.slicequeue.sq_user.user.command.domain.type.UserState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Map;

@DataJpaTest
class UserTest {

    @Autowired
    TestEntityManager entityManager;

    @Test
    void insertAndSelect() {
        // given
        Long projectId = 100L;
        String loginId = "test_user";
        String pwd = "hashed_password"; // 실제로는 해시 처리
        String nickname = "테스트유저";
        UserState state = UserState.ACTIVE;

        Map<String, Object> profileJson = Map.of(
                "imageUrl", "https://example.com/img.jpg",
                "bio", "소개글입니다"
        );

        User user = User.create(projectId, state, loginId, pwd, nickname, profileJson);

        // when
        entityManager.persist(user);
        entityManager.flush();
        entityManager.clear();

        UserId generatedUserId = user.getUserId();
        User found = entityManager.find(User.class, generatedUserId);

        // then
        assert found != null;
        assert found.getUserId().equals(generatedUserId);
        assert found.getLoginId().equals(loginId);
        assert found.getProfile().get("bio").equals("소개글입니다");
    }
}
