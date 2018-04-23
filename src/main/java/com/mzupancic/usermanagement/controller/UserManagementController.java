package com.mzupancic.usermanagement.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.mzupancic.usermanagement.model.Privilege;
import com.mzupancic.usermanagement.model.User;
import com.mzupancic.usermanagement.repository.PrivilegeRepository;
import com.mzupancic.usermanagement.repository.UserRepository;
import com.mzupancic.usermanagement.service.PrivilegeService;
import com.mzupancic.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-mgmt")
@PreAuthorize("hasAnyAuthority('USER')")
public class UserManagementController {

    private UserRepository userRepository;
    private UserService userService;
    private PrivilegeRepository privilegeRepository;
    private PrivilegeService privilegeService;

    @Autowired
    public UserManagementController(final UserRepository userRepository, UserService userService, PrivilegeRepository privilegeRepository, PrivilegeService privilegeService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.privilegeRepository = privilegeRepository;
        this.privilegeService = privilegeService;
    }

    @GetMapping("/users")
    @JsonView(User.Views.Manage.class)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    @JsonView(User.Views.Manage.class)
    public User getUser(@RequestParam final String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/add-user")
    @JsonView(User.Views.Add.class)
    public void addUser(@RequestBody @Valid User newUser) {
        userService.addUser(newUser);
    }

    @PostMapping("/modify-user")
    @JsonView(User.Views.Manage.class)
    public void modifyUser(@RequestBody User modifiedUser) {
        userService.modifyUser(modifiedUser);
    }

    @DeleteMapping("/user/{username}")
    public void deleteUser(@PathVariable final String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/privileges")
    @JsonView(Privilege.Views.Manage.class)
    public List<Privilege> getPrivileges() {
        return privilegeRepository.findAll();
    }

    @PostMapping("/privilege/add")
    @JsonView(Privilege.Views.Manage.class)
    public void addPrivilege(@RequestBody Privilege privilege) {
        privilegeService.addPrivilege(privilege);
    }

}
