package com.fill.EX2.controller;

import com.fill.EX2.entity.User;
import com.fill.EX2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;


    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "usersList";
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "userDetail";
    }

    @GetMapping("/addUser")
    public String createUserPage() {
        return "createUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/all";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users/all";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "editUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/user/" + user.getId();
    }



}