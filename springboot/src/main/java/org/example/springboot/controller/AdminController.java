package org.example.springboot.controller;


import org.example.springboot.model.User;
import org.example.springboot.service.RoleService;
import org.example.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String adminHomeAndAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("id") Long id, User user) {
        User existingUser = userService.getUserById(id);
        userService.saveUser(existingUser, user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(User user) {
        userService.deleteUser(user);
        return "redirect:/admin";
    }
}
