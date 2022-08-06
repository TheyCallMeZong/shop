package com.example.shop.controllers;

import com.example.shop.models.dto.UserDto;
import com.example.shop.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserDetailsServiceImpl userService;

    @Autowired
    public RegistrationController(UserDetailsServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userDto") UserDto userDto, Model model){
        var user = userService.getUser(userDto.getEmail());
        if (user != null){
            return "err";
        }
        userService.addUser(userDto);
        return "login";
    }
}
