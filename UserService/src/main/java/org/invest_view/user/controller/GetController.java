package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user-operator/v1/get")
public class GetController {

    @Autowired
    UserService userService;

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
}
