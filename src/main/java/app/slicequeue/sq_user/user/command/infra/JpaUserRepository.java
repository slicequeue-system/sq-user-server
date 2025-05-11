package app.slicequeue.sq_user.user.command.infra;

import app.slicequeue.sq_user.user.command.domain.User;
import app.slicequeue.sq_user.user.command.domain.UserId;
import app.slicequeue.sq_user.user.command.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends UserRepository, JpaRepository<User, UserId> {

    User save(User user);
}
