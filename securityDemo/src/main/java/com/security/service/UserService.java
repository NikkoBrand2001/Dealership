package com.security.service;


import com.security.config.JwtUtil.JwtHelper;
import com.security.model.dtos.AuthRequest;
import com.security.model.dtos.RoleRequest;
import com.security.model.dtos.UserRequest;
import com.security.model.dtos.UserResponse;
import com.security.model.entities.RoleEnum;
import com.security.model.entities.UserEntity;
import com.security.repository.UserEntityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;


    public UserResponse findById(Long id) {
        Optional<UserEntity> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            log.info("User found with ID: {}", id);
            return mapToResponse(userOptional.get());
        } else {
            log.error("Cannot find user with ID: {}", id);
            throw new RuntimeException("Cannot find user with ID: " + id);
        }
    }

    public String saveUser(@NonNull UserRequest userRequest) {

        if (isUserNameAlreadyRegistered(userRequest)) {
            log.warn("Username: {} is already used", userRequest.getUsername());
            return "Username: " + userRequest.getUsername() + " is already used";
        }

        UserEntity userentity = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .isEnabled(userRequest.isEnabled())
                .isAccountNoExpired(userRequest.isAccountNoExpired())
                .isAccountNoLocked(userRequest.isAccountNoLocked())
                .isCredentialNoExpired(userRequest.isCredentialNoExpired())
                .roles(userRequest.getRoles())
                .build();
        repository.save(userentity);
        log.info("User registered successfully with username: {}", userRequest.getUsername());
        return "User register successful";
    }


    public String loginUser(AuthRequest authRequest) {
        UserEntity user = repository.findByUsername(authRequest.username())
                .orElseThrow(() -> {
                    log.error("Username: {} is invalid", authRequest.username());
                    return new RuntimeException("Username: " + authRequest.username() + " is invalid");
                });


        if (passwordEncoder.matches(user.getPassword(), authRequest.password())) {
            log.info("User logged in successfully with username: {}", authRequest.username());
            return jwtHelper.generateToken(user);
        }
        log.error("Username or password is invalid for username: {}", authRequest.username());
        return "Username or password is invalid";
    }


    public boolean validateToken(String token) {
        log.info("staring token validation");
        return jwtHelper.validateToken(token);
    }

    public List<UserResponse> findAll() {
        List<UserResponse> listResponse = repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
        log.info("returning list with all users");
        return listResponse;
    }

    public String changeUserRole(@NonNull Long id, RoleRequest roleRequest) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID " + id + " is invalid"));
        user.getRoles().forEach(role -> role.setRoleName(mapToEnum(roleRequest)));
        repository.save(user);
        log.info("User role has been changed for user ID: {}", id);
        return "Role change successful";
    }

    public String deleteUserById(@NonNull Long id) {
        repository.deleteById(id);

        log.info("a user has delete from system");

        return "user remove from system";
    }

    private boolean isUserNameAlreadyRegistered(UserRequest userRequest) {
        List<String> listAuth = repository.findAll().stream()
                .map(UserEntity::getUsername)
                .toList();

        return listAuth.contains(userRequest.getUsername());
    }

    private RoleEnum mapToEnum(RoleRequest request) {
        return switch (request.roleName().toUpperCase()) {
            case "ROLE_ADMIN" -> RoleEnum.ROLE_ADMIN;
            case "ROLE_EMPLOYEE" -> RoleEnum.ROLE_EMPLOYEE;
            case "ROLE_INVITED" -> RoleEnum.ROLE_INVITED;
            default -> RoleEnum.ROLE_USER;
        };

    }


    private UserResponse mapToResponse(UserEntity user) {
        return UserResponse.builder()
                .Id(user.getId())
                .password(user.getPassword())
                .isEnabled(user.isEnabled())
                .isAccountNoExpired(user.isAccountNoExpired())
                .isAccountNoLocked(user.isAccountNoLocked())
                .isCredentialNoExpired(user.isCredentialNoExpired())
                .roles(user.getRoles())
                .build();
    }
}
