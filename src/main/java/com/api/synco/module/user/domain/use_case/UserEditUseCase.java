package com.api.synco.module.user.domain.use_case;

import com.api.synco.module.user.application.dto.edit.UserEditRequest;
import com.api.synco.module.user.domain.UserEntity;
import com.api.synco.module.user.domain.exception.UserNotFoundException;
import com.api.synco.module.user.domain.exception.permission.UserWithoutEditPermissionException;
import com.api.synco.module.user.domain.port.UserRepository;
import com.api.synco.module.user.domain.vo.Email;
import com.api.synco.module.user.domain.vo.Name;
import org.springframework.stereotype.Component;

@Component
public class UserEditUseCase {

    private final UserRepository userRepository;

    public UserEditUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This Method is responsible for executing the
     * use case of edit a user
     *
     * <p> In this use case will happen the validation if user
     * can edit another, or himself, user. After the validation
     * the user will be edit and save in Database.</p>
     *
     * @param userEditRequest Data of the edit
     * @param idUserAutenticated id of the user who will edit
     * @return The user edited
     */
    public UserEntity execute(UserEditRequest userEditRequest, long idUserAutenticated) {
        UserEntity userAuthenticated = userRepository.findById(idUserAutenticated).orElseThrow(() -> new UserNotFoundException(idUserAutenticated));
        UserEntity userEdit = userRepository.findById(userEditRequest.id()).orElseThrow( () -> new UserNotFoundException(userEditRequest.id()));

        if(!canEditUser(userAuthenticated, userEdit) ) throw new UserWithoutEditPermissionException();

        editUser(userEdit, userEditRequest);

        userRepository.save(userEdit);

        return userEdit;

    }

    private void editUser(UserEntity userEdit, UserEditRequest userEditRequest) {
        Name name = new Name(userEditRequest.name());
        Email email = new Email(userEditRequest.email());

        userEdit.setName(name);
        userEdit.setEmail(email);
    }

    private boolean canEditUser(UserEntity userAutenticated, UserEntity userEdit) {
        if(userAutenticated.canEditUser()) return true;

        return userEdit.equals(userAutenticated);
    }

}
