package com.api.blog.module.authentication.application.controller;

import com.api.blog.core.UserAuthenticationService;
import com.api.blog.infrastructure.api.CustomApiResponse;
import com.api.blog.module.authentication.application.dto.login.UserLoginRequest;
import com.api.blog.module.authentication.application.dto.login.UserLoginResponse;
import com.api.blog.module.authentication.application.dto.register.UserRegisterRequest;
import com.api.blog.module.authentication.application.dto.register.UserRegisterResponse;
import com.api.blog.module.authentication.application.dto.reset_password.UserResetRequest;
import com.api.blog.module.authentication.domain.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/blog/auth")
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserAuthenticationService userAuthenticationService;

    public AuthenticationController(AuthenticationService authenticationService, UserAuthenticationService userAuthenticationService) {
        this.authenticationService = authenticationService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register new user",
            description = "Creates a new user in the system with the provided credentials. Returns the details of the newly created user."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserRegisterResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data (e.g., missing fields, weak password, or validation error)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already registered",
                    content = @Content
            )
    })
    public ResponseEntity<CustomApiResponse<UserRegisterResponse>> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        var user = authenticationService.register(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomApiResponse.success(HttpStatus.CREATED.value(), "User created successfully", user));
    }


    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Authenticates a user and returns a JWT token for access to protected endpoints"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Login successful",
                    content = @Content(schema = @Schema(implementation = UserLoginResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials",
                    content = @Content
            )
    })
    public ResponseEntity<CustomApiResponse<UserLoginResponse>> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        var user = authenticationService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomApiResponse.success(HttpStatus.CREATED.value(), "Login successful", user));
    }

    @PatchMapping("/password")
    @Operation (
            summary = "Reset password",
            description = "Reset the password of user authenticated"
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "202",
                    description = "Reset executed with success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The password is incorrect",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The password is not valid",
                    content = @Content
            )
    })
    public ResponseEntity<CustomApiResponse<Void>> resetPassword(@RequestBody @Valid UserResetRequest userResetRequest){
        long idUser = userAuthenticationService.getAuthenticatedUserId();

        authenticationService.resetPassword(userResetRequest, idUser);

        return ResponseEntity.ok(CustomApiResponse.success(HttpStatus.ACCEPTED.value(), "Reset executed with success"));
    }

}