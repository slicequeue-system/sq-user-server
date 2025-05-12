package app.slicequeue.sq_user.user.command.presentation;

import app.slicequeue.common.dto.CommonResponse;
import app.slicequeue.sq_user.user.command.application.service.CreateUserService;
import app.slicequeue.sq_user.user.command.application.service.UpdateUserService;
import app.slicequeue.sq_user.user.command.application.usecase.UpdateUserInfoUseCase;
import app.slicequeue.sq_user.user.command.domain.UserId;
import app.slicequeue.sq_user.user.command.domain.dto.CreateUserCommand;
import app.slicequeue.sq_user.user.command.domain.dto.UpdateUserCommand;
import app.slicequeue.sq_user.user.command.presentation.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final CreateUserService createUserService;
    private final UpdateUserInfoUseCase updateUserInfoUseCase;

    @PostMapping
    public CommonResponse<String> create(@RequestBody @Valid UserRequest request) {
        CreateUserCommand command = CreateUserCommand.from(request);
        return CommonResponse.success("created.", createUserService.createUser(command).getUserId().toString());
    }

    @PutMapping("{userId}")
    public CommonResponse<String> update(@RequestBody @Valid UserRequest request, @PathVariable Long userId) {
        UpdateUserCommand command = UpdateUserCommand.from(request, UserId.from(userId));
        return CommonResponse.success("updated.", updateUserInfoUseCase.execute(command).toString());
    }
}
