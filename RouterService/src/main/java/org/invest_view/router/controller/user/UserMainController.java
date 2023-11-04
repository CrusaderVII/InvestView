package org.invest_view.router.controller.user;

import org.invest_view.router.feignclient.UserClient;
import org.invest_view.router.model.IssuerDataResponse;
import org.invest_view.router.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("invest-view/api/user/v1/main")
public class UserMainController {

    @Autowired
    UserClient userClient;

    @GetMapping("/user/id")
    public UserResponse getUserById(@RequestParam Long id) {
        return userClient.getUserById(id);
    }

    @GetMapping("/issuer")
    public IssuerDataResponse getIssuer(@RequestParam String id) {
        return getIssuer(id);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userClient.getAllUsers();
    }
}
