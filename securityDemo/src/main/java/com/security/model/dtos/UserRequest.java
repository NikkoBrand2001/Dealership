package com.security.model.dtos;

import com.security.model.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequest {
    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNoExpired;
    private boolean isAccountNoLocked;
    private boolean isCredentialNoExpired;
    private Set<RoleEntity> roles = new HashSet<>();
}
