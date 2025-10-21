package com.api.blog.module.authentication.application.controller;

import com.api.blog.module.authentication.application.dto.login.UserLoginRequest;
import com.api.blog.module.authentication.application.dto.login.UserLoginResponse;
import com.api.blog.module.authentication.application.dto.register.UserRegisterRequest;
import com.api.blog.module.authentication.application.dto.register.UserRegisterResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/blog/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e login de usuários")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @Operation(
        summary = "Registrar novo usuário",
        description = "Cria um novo usuário no sistema com as credenciais fornecidas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
            content = @Content(schema = @Schema(implementation = UserRegisterResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
            content = @Content)
    })
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        var user = authenticationService.register(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    @Operation(
        summary = "Fazer login",
        description = "Autentica um usuário e retorna um token JWT para acesso aos endpoints protegidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Login realizado com sucesso",
            content = @Content(schema = @Schema(implementation = UserLoginResponse.class))),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
            content = @Content)
    })
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        var user = authenticationService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

}
