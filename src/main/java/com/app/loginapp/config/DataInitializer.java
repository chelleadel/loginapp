package com.app.loginapp.config;

import com.app.loginapp.controllers.AuthController;
import com.app.loginapp.models.ERole;
import com.app.loginapp.models.Role;
import com.app.loginapp.payload.request.SignupRequest;
import com.app.loginapp.repository.RoleRepository;
import com.app.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthController authController;

    public DataInitializer(RoleRepository roleRepository, AuthController authController, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authController = authController;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check and save roles only if they do not exist
        if (roleRepository.findByName(ERole.USER).isEmpty()) {
            Role userRole = new Role(ERole.USER);
            roleRepository.save(userRole);
        }
        if (roleRepository.findByName(ERole.MANAGER).isEmpty()) {
            Role managerRole = new Role(ERole.MANAGER);
            roleRepository.save(managerRole);
        }

        // Check users before registering
        if (userRepository.findByUsername("user").isEmpty()) {
            authController.registerUser(new SignupRequest("user", "Anna", "user", "user"));
        }
        if (userRepository.findByUsername("manager").isEmpty()) {
            authController.registerUser(new SignupRequest("manager", "Else", "manager", "manager"));
        }
    }
}