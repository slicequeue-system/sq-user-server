package app.slicequeue.sq_user.user.command.application.service;

import app.slicequeue.common.exception.NotFoundException;
import app.slicequeue.sq_user.user.command.domain.User;
import app.slicequeue.sq_user.user.command.domain.UserRepository;
import app.slicequeue.sq_user.user.command.domain.dto.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User updateUser(UpdateUserCommand command) {
        User user = userRepository.findByUserId(command.userId()).orElseThrow(
                () -> new NotFoundException("사용자가 존재하지 않습니다."));
        user.update(command, passwordEncoder);
        return userRepository.save(user);
    }
}
