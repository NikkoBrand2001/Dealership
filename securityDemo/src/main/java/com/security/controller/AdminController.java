package com.security.controller;


import com.security.model.dtos.RoleRequest;
import com.security.model.dtos.UserResponse;
import com.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor

public class AdminController {
    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PostMapping("/change-role/{id}")
    public ResponseEntity<String> changeUserRoles(@PathVariable Long id, @RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(userService.changeUserRole(id, roleRequest));
    }


}
