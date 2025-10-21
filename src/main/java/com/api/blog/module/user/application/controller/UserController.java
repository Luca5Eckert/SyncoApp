package com.api.blog.module.user.application.controller;

import com.api.blog.core.UserAuthenticationService;
import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.application.dto.edit.UserEditRequest;
import com.api.blog.module.user.application.dto.edit.UserEditResponse;
import com.api.blog.module.user.application.dto.get.UserGetResponse;
import com.api.blog.module.user.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/blog/users")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@SecurityRequirement(name = "bearer-jwt")
public class UserController {

    private final UserService userService;
    private final UserAuthenticationService authenticationService;

    public UserController(UserService userService, UserAuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @Operation(
        summary = "Criar novo usuário",
        description = "Cria um novo usuário no sistema. Requer autenticação."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
            content = @Content(schema = @Schema(implementation = UserCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
            content = @Content),
        @ApiResponse(responseCode = "401", description = "Não autenticado",
            content = @Content)
    })
    public ResponseEntity<UserCreateResponse> create(@RequestBody @Valid UserCreateRequest userCreateRequest){
        var response = userService.create(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping
    @Operation(
        summary = "Deletar usuário",
        description = "Remove um usuário do sistema. Apenas o próprio usuário ou um administrador podem deletar."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado",
            content = @Content),
        @ApiResponse(responseCode = "403", description = "Sem permissão para deletar este usuário",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content)
    })
    public ResponseEntity<?> delete(@RequestBody @Valid UserDeleteRequest userDeleteRequest){
        long idUserAutenticated = authenticationService.getAuthenticatedUserId();

        userService.delete(userDeleteRequest, idUserAutenticated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User deleted with success");
    }

    @PatchMapping
    @Operation(
        summary = "Editar usuário",
        description = "Atualiza as informações de um usuário. Apenas o próprio usuário ou um administrador podem editar."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Usuário atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = UserEditResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
            content = @Content),
        @ApiResponse(responseCode = "401", description = "Não autenticado",
            content = @Content),
        @ApiResponse(responseCode = "403", description = "Sem permissão para editar este usuário",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content)
    })
    public ResponseEntity<UserEditResponse> edit(@RequestBody @Valid UserEditRequest userEditRequest){
        long idUserAutenticated = authenticationService.getAuthenticatedUserId();

        var response = userService.edit(userEditRequest, idUserAutenticated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar usuário por ID",
        description = "Retorna os dados de um usuário específico pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado",
            content = @Content(schema = @Schema(implementation = UserGetResponse.class))),
        @ApiResponse(responseCode = "401", description = "Não autenticado",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content)
    })
    public ResponseEntity<UserGetResponse> get(
        @Parameter(description = "ID do usuário", required = true)
        @PathVariable("id") long id){
        var user = userService.get(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    @Operation(
        summary = "Listar todos os usuários",
        description = "Retorna uma lista com todos os usuários cadastrados no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
            content = @Content(schema = @Schema(implementation = UserGetResponse.class))),
        @ApiResponse(responseCode = "401", description = "Não autenticado",
            content = @Content)
    })
    public ResponseEntity<List<UserGetResponse>> getAll(){
        var users = userService.getAll();
        return ResponseEntity.ok(users);
    }


}
