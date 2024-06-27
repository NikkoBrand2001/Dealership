package com.security.model.dtos;

import com.security.model.entities.PermissionEntity;
import com.security.model.entities.RoleEnum;
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
public class RoleResponse {
    private Long id;
    private RoleEnum roleName;
    Set<PermissionEntity> permissions = new HashSet<>();
}
