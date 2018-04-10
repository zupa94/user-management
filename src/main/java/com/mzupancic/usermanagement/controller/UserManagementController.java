package com.mzupancic.usermanagement.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.mzupancic.usermanagement.model.User;
import com.mzupancic.usermanagement.repository.UserRepository;
import com.mzupancic.usermanagement.service.UserService;
import com.mzupancic.usermanagement.views.Views.Manage;
import com.mzupancic.usermanagement.views.Views.Add;
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
    @JsonView(Manage.class)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    @JsonView(Manage.class)
    public User getUser(@RequestParam final Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add-user")
    @JsonView(Add.class)
    public void addUser(@RequestBody @Valid User newUser) {
        userService.addUser(newUser);
    }

    @PostMapping("/modify-user")
    @JsonView(Manage.class)
    public void modifyUser(@RequestBody User modifiedUser){
        userService.modifyUser(modifiedUser);
    }

    @DeleteMapping("/user/{username}")
    public void deleteUser(@PathVariable final String username){
        userService.deleteUser(username);
    }

}
