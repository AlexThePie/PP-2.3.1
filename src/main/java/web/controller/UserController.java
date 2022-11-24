package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
       userService.removeUser(id);
       return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
       User user = userService.findById(id);
       model.addAttribute("user", user);
       return "update";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userService.changeUser(user);
        return "redirect:/";
    }




}
