package com.mzupancic.usermanagement.controller;


import com.mzupancic.usermanagement.model.User;
import com.mzupancic.usermanagement.repository.UserRepository;
import com.mzupancic.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-mgmt")
public class UserManagementController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserManagementController(final UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam final Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add-user")
    public void addUser(@RequestBody @Valid User newUser) {
        userService.addUser(newUser);
    }

    @PostMapping("/modify-user")
    public void modifyUser(@RequestBody User modifiedUser){
        userService.modifyUser(modifiedUser);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
    }

}
