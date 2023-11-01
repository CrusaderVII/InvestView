package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.repository.service.CloudService;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("api/user-service/v1/get")
public class GetController {

    @Autowired
    UserService userService;

    @Autowired
    CloudService cloudService;

    @GetMapping("/user/id")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.getUserByEmailAndPassword(email, password);

        return user;
    }

    @GetMapping("/user/issuers")
    public List<IssuerData> getIssuerDataOfUser(@RequestParam Long id) {
        User user = userService.getUserById(id);

        return user.getIssuersData();
    }

    @GetMapping("/issuer")
    public IssuerData getIssuer(@RequestParam String id) {
        return userService.getIssuerDataBySecId(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(cloudService.uploadFile(file), HttpStatus.OK);
    }
}
