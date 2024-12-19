package com.study_flow.modules.user.service;

import com.study_flow.exceptions.EmailAlreadyExistsException;
import com.study_flow.exceptions.UsernameAlreadyExistsException;
import com.study_flow.modules.user.repository.UserRepository;
import com.study_flow.modules.user.dtos.UserRequestPayload;
import com.study_flow.modules.user.dtos.UserResponse;
import com.study_flow.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserResponse createNewUser(UserRequestPayload requestPayload) {
        validateEmailUniqueness(requestPayload.email());
        validateUsernameUniqueness(requestPayload.username());

        var user = convertToUser(requestPayload);
        repository.save(user);

        return new UserResponse(user.getId());
    }

    public void deleteUser(UUID id){
        validateId(id);
        repository.deleteById(id);
    }

    //o id vai vir do token e o email vai vir do EmailUpdateRequest(String newEmail)
    public void alterEmail(UUID id, String newEmail){

    }

    //o id vai vir do token e o email vai vir do UsernameUpdateRequest(String oldPassword, String newPassword)
    public void alterPassword(UUID id, String oldPassword, String newPassword){

    }

    public void validateId(UUID id){

    }

    private void validateEmailUniqueness(String email) {
        repository.findByEmail(email)
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException(
                            String.format("Email '%s' is already registered", email)
                    );
                });
    }

    private void validateUsernameUniqueness(String username){
        repository.findByUsername(username)
                .ifPresent(user -> {
                    throw new UsernameAlreadyExistsException(
                            String.format("Username '%s' is already taken", username)
                    );
                });
    }

    private User convertToUser(UserRequestPayload requestPayload) {
        return User.builder()
                .username(requestPayload.username())
                .email(requestPayload.email())
                .password(requestPayload.password())
                .build();
    }
}
