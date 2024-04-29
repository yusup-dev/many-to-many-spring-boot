package com.manytomany.controller;

import com.manytomany.entity.UserEntity;
import com.manytomany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<UserEntity> list = userRepository.findAll();
        model.addAttribute("listUser", list);
        return "users";
    }
}
