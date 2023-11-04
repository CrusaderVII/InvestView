package org.invest_view.router.controller.user;

import org.invest_view.router.feignclient.UserClient;
import org.invest_view.router.model.IssuerDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("invest-view/api/user/v1/issuer")
public class IssuerController {

    @Autowired
    UserClient userClient;

    @PostMapping("/add")
    public IssuerDataResponse addIssuerToUser(@RequestParam String userName, @RequestParam String secId) {
        return userClient.addIssuerToUser(userName, secId);
    }

    @PostMapping("/save/all")
    public void saveAllIssuers(){
        userClient.saveAllIssuers();
    }

    @GetMapping("/get/all")
    public List<IssuerDataResponse> getAllIssuers() {
        return userClient.getAllIssuers();
    }

    @DeleteMapping("/remove")
    public void removeIssuerFromUser(@RequestParam String userName, @RequestParam String secId) {
        userClient.removeIssuerFromUser(userName, secId);
    }

    @PostMapping("/issuer/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                             @RequestParam String secId) {
        return userClient.uploadFile(file, secId);
    }

}
