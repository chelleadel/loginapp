package com.app.loginapp.controllers;

import com.app.loginapp.models.User;
import com.app.loginapp.repository.RoleRepository;
import com.app.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER')")
    public List<String> userAccess() {
        return roleRepository.findAll().stream().map((role -> role.getName().name())).toList();
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('MANAGER')")
    public List<String> managerAccess() {
        return userRepository.findAll().stream().map((user -> user.getUsername())).toList();
    }
}
