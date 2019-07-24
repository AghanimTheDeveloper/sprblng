package com.aghanim.controller;

import com.aghanim.model.User;
import com.aghanim.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String usersList(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("list", users);
        return "admin";
    }

    @GetMapping(value = "/add")
    public String userFormForAdd (Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/edit")
    public String userFormForUpdate(@RequestParam("id") long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
