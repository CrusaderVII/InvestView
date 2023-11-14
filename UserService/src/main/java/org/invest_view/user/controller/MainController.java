package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.model.ValidationToken;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-service/v1/main")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/user/id")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/issuer")
    public IssuerData getIssuer(@RequestParam String id) {
        return userService.getIssuerDataBySecId(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/token")
    public ResponseEntity<String> getUserWithToken(@RequestParam String name) {
        User user = userService.getUserByName(name);
        ValidationToken token = user.getToken();

        return new ResponseEntity<>(user.getEmail()+": "+token.getToken()+" - "+token.getGeneratedAt(), HttpStatus.OK);
    }
}
