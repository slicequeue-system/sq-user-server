package app.slicequeue.sq_user.user.command.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User save(User user);
}
