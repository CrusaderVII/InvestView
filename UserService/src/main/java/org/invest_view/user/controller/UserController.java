package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-service/v1/user")
@CrossOrigin(originPatterns = "http://localhost:5173/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/auth")
    public User authenticationUser(@RequestParam String email, @RequestParam String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

    @GetMapping("/issuers")
    public List<IssuerData> getUsersBookmarks(@RequestParam String userName) {

        return userService.getUserByName(userName)
                .getIssuersData();
    }

    @DeleteMapping("/delete")
    public User deleteUserByName(@RequestParam String userName) {
        return userService.deleteUserByName(userName);
    }
}
