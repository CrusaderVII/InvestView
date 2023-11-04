package org.invest_view.router.controller.user;

import org.invest_view.router.feignclient.UserClient;
import org.invest_view.router.model.IssuerDataResponse;
import org.invest_view.router.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invest-view/api/user/v1/user")
public class UserController {

    @Autowired
    UserClient userClient;

    @GetMapping("/auth")
    public UserResponse authenticationUser(@RequestParam String email, @RequestParam String password) {
        return userClient.authenticationUser(email, password);
    }

    @PostMapping("/save")
    public UserResponse saveUser(@RequestBody UserResponse user) {
        return userClient.saveUser(user);
    }

    @GetMapping("/issuers")
    public List<IssuerDataResponse> getUsersBookmarks(@RequestParam String userName) {
        return userClient.getUsersBookmarks(userName);
    }

    @DeleteMapping("/delete")
    public UserResponse deleteUserByName(@RequestParam String userName) {
        return userClient.deleteUserByName(userName);
    }

}
