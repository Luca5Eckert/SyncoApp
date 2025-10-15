package com.api.blog.module.user.application.controller;

import com.api.blog.core.UserAuthenticationService;
import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.application.dto.edit.UserEditRequest;
import com.api.blog.module.user.application.dto.edit.UserEditResponse;
import com.api.blog.module.user.application.dto.get.UserGetResponse;
import com.api.blog.module.user.domain.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/blog/users")
public class UserController {

    private final UserService userService;
    private final UserAuthenticationService authenticationService;

    public UserController(UserService userService, UserAuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<UserCreateResponse> create(@RequestBody @Valid UserCreateRequest userCreateRequest){
        var response = userService.create(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid UserDeleteRequest userDeleteRequest){
        long idUserAutenticated = authenticationService.getAuthenticatedUserId();

        userService.delete(userDeleteRequest, idUserAutenticated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User deleted with success");
    }

    @PatchMapping
    public ResponseEntity<UserEditResponse> edit(@RequestBody @Valid UserEditRequest userEditRequest){
        long idUserAutenticated = authenticationService.getAuthenticatedUserId();

        var response = userService.edit(userEditRequest, idUserAutenticated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> get(@PathVariable("id") long id){
        var user = userService.get(id);
        return ResponseEntity.ok(user);
    }

}
