package com.shailendra.writerous.controller;

import com.shailendra.writerous.dto.RegistrationDto;
import com.shailendra.writerous.entity.User;
import com.shailendra.writerous.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto registrationDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findByEmail(registrationDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "Email already exists");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", registrationDto);
            return "register";
        }
        userService.saveUser(registrationDto);
        return "redirect:/register?success";
    }
}
