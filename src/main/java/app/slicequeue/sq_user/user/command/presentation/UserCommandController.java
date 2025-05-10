package app.slicequeue.sq_user.user.command.presentation;

import app.slicequeue.common.dto.CommonResponse;
import app.slicequeue.sq_user.user.command.application.service.CreateUserService;
import app.slicequeue.sq_user.user.command.domain.dto.CreateUserCommand;
import app.slicequeue.sq_user.user.command.presentation.dto.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/v1/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final CreateUserService createUserService;


    @PostMapping
    public CommonResponse<String> create(@RequestBody @Valid CreateUserRequest request) {
        CreateUserCommand command = CreateUserCommand.from(request);
        return CommonResponse.success(createUserService.createUser(command).toString());
    }
}
