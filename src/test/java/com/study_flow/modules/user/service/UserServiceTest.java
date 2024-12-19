package com.study_flow.modules.user.service;

import com.study_flow.exceptions.EmailAlreadyExistsException;
import com.study_flow.exceptions.UsernameAlreadyExistsException;
import com.study_flow.modules.user.dtos.UserRequestPayload;
import com.study_flow.modules.user.entity.User;
import com.study_flow.modules.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Test
    @DisplayName("Should create a new user")
    void should_create_a_new_user() {
        var user = User.builder()
                .username("test123")
                .password("TT#ajkfresm")
                .email("example@gmail.com")
                .id(UUID.randomUUID())
                .build();

        when(repository.save(any())).thenReturn(user);

        var requestPayload = new UserRequestPayload("test123", "example@gmail.com", "TT#ajkfresm");

        var result = service.createNewUser(requestPayload);

        verify(repository, times(1)).save(any());
        assertEquals(user.getId().toString(), result.toString());
    }

    @Test
    @DisplayName("Should throws a UsernameAlreadyExistsException")
    void should_throws_a_UsernameAlreadyExistsException(){
        var requestPayload = new UserRequestPayload("test123", "example@gmail.com", "TT#ajkfresm");

        when(repository.findByUsername(requestPayload.username())).thenReturn(Optional.of(new User()));

        var thrown = assertThrows(UsernameAlreadyExistsException.class, ()->{
            service.createNewUser(requestPayload);
        });

        assertEquals("Username 'test123' is already taken", thrown.getMessage());
    }

    @Test
    @DisplayName("Should throws a EmailAlreadyExistsException")
    void should_throws_a_EmailAlreadyExistsException(){
        var requestPayload = new UserRequestPayload("test123", "example@gmail.com", "TT#ajkfresm");

        when(repository.findByEmail(requestPayload.email())).thenReturn(Optional.of(new User()));

        var thrown = assertThrows(EmailAlreadyExistsException.class, ()->{
            service.createNewUser(requestPayload);
        });

        assertEquals("Email 'example@gmail.com' is already taken", thrown.getMessage());
    }
}