package com.charity2.Controller;

import com.charity2.Service.UserResponse;
import com.charity2.Service.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllUsersExceptSelf(authentication));
    }



}
