package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
           return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "new";
        }
        userService.add(user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap modelMap, @PathVariable("id") long id) {
        modelMap.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if(bindingResult.hasErrors()) {
            return "edit";
        }

        userService.edit(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
