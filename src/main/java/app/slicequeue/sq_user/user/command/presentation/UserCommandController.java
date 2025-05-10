package app.slicequeue.sq_user.user.command.presentation;

import app.slicequeue.common.dto.CommonResponse;
import app.slicequeue.sq_user.user.command.application.service.CreateUserService;
import app.slicequeue.sq_user.user.command.application.service.UpdateUserService;
import app.slicequeue.sq_user.user.command.domain.UserId;
import app.slicequeue.sq_user.user.command.domain.dto.CreateUserCommand;
import app.slicequeue.sq_user.user.command.domain.dto.UpdateUserCommand;
import app.slicequeue.sq_user.user.command.presentation.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/v1/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;


    @PostMapping
    public CommonResponse<String> create(@RequestBody @Valid UserRequest request) {
        CreateUserCommand command = CreateUserCommand.from(request);
        return CommonResponse.success(createUserService.createUser(command).getUserId().toString());
    }

    @PutMapping("{userId}")
    public CommonResponse<String> update(@RequestBody @Valid UserRequest request, @PathVariable Long userId) {
        UpdateUserCommand command = UpdateUserCommand.from(request, UserId.from(userId));
        return CommonResponse.success(updateUserService.updateUser(command).getUserId().toString());
    }
}
