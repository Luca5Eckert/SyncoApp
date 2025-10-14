package com.api.blog.module.user.application.controller;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/blog/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> create(@RequestBody @Valid UserCreateRequest userCreateRequest){
        var response = userService.create(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
