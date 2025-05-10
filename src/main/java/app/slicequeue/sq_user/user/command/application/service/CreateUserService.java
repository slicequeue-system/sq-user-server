package app.slicequeue.sq_user.user.command.application.service;

import app.slicequeue.sq_user.user.command.domain.User;
import app.slicequeue.sq_user.user.command.domain.UserRepository;
import app.slicequeue.sq_user.user.command.domain.dto.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(CreateUserCommand command) {
        return userRepository.save(User.create(command, passwordEncoder));
    }
}
