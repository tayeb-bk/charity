package com.charity2.Controller;

import com.charity2.Service.UserService;
import com.charity2.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
     UserService userService;


    @GetMapping("/getallUser")
    @PreAuthorize("hasRole('client_admin')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



    @PostMapping("/addUser")
    @PreAuthorize("hasRole('client_admin')")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


}
