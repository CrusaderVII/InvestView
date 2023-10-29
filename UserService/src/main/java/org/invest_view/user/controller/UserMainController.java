package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-service/v1/main")
@CrossOrigin(originPatterns = "http://localhost:5173/")
public class UserMainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
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

//    @GetMapping("save/all-issuers")
//    public void saveAllIssuers() {
//        List<IssuerData> list = new ArrayList<>();
//
//        list = RequestConstructor.getAllIssuersMetadataResponse(list.getClass());
//
//        list.stream()
//                .forEach(x -> issuerRepository
//                        .save(new IssuerData(x.getSecId(), x.getFullName())));
//    }

    @GetMapping("add/issuer")
    public void addIssuerToUser(@RequestParam String userName,
                                @RequestParam String secId) {

        userService.saveIssuerToUser(userName, secId);
    }

    @DeleteMapping("/delete/issuer")
    public void deleteIssuerFromUser(@RequestParam String userName, @RequestParam String secId) {

        userService.deleteIssuerFromUser(userName, secId);
    }
}
