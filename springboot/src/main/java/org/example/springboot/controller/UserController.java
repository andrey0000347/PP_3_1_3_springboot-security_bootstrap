package org.example.springboot.controller;

import org.example.springboot.model.User;
import org.example.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = userService.getUserByFirstname(principal.getName());
        model.addAttribute("user", user);
        Boolean hasRole = user.getAuthorities().stream().anyMatch(g -> g.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRole", hasRole);
        return "user";
    }
}