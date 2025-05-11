package app.slicequeue.sq_user.user.command.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    User save(User user);

    Optional<User> findByUserId(UserId userId);
}
