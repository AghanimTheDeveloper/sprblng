package com.aghanim.controller;

import com.aghanim.model.MyUserDetails;
import com.aghanim.model.User;
import com.aghanim.service.abstraction.SecurityService;
import com.aghanim.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "/index")
    public String welcome(Model model){
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "You are entering Aghanim's dome");
        return "index";
    }

    @GetMapping(value = "/signup")
    public String registration(){
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(User userForm, HttpServletRequest req, HttpServletResponse resp){
        userService.saveUser(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getPassword(), req, resp);
        return "redirect:/user";
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/postLogin")
    public String postLogin(Model model, HttpSession session){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((MyUserDetails) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUser", loggedInUser.getName());
        session.setAttribute("user", loggedInUser);
        return "redirect:/user?id=" + loggedInUser.getId();
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }

    @GetMapping(value = "/user")
    public String userPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user";
    }

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
