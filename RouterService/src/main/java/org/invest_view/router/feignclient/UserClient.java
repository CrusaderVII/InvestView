package org.invest_view.router.feignclient;

import org.invest_view.router.model.IssuerDataResponse;
import org.invest_view.router.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "USER-SERVICE", path = "api/user-service/v1")
public interface UserClient {

    //MAIN-controller

    @GetMapping("/main/user/id")
    public UserResponse getUserById(@RequestParam Long id);

    @GetMapping("/main/issuer")
    public IssuerDataResponse getIssuer(@RequestParam String id);

    @GetMapping("/main/users")
    public List<UserResponse> getAllUsers();

    //USER-controller

    @GetMapping("/user/auth")
    public UserResponse authenticationUser(@RequestParam String email, @RequestParam String password);

    @PostMapping("/user/save")
    public UserResponse saveUser(@RequestBody UserResponse user);

    @GetMapping("/user/issuers")
    public List<IssuerDataResponse> getUsersBookmarks(@RequestParam String userName);

    @DeleteMapping("/user/delete")
    public UserResponse deleteUserByName(@RequestParam String userName);

    //ISSUER-controller

    @PostMapping("/issuer/add")
    public IssuerDataResponse addIssuerToUser(@RequestParam String userName, @RequestParam String secId);

    @PostMapping("/issuer/save/all")
    public void saveAllIssuers();

    @GetMapping("/issuer/get/all")
    public List<IssuerDataResponse> getAllIssuers();

    @DeleteMapping("/issuer/remove")
    public void removeIssuerFromUser(@RequestParam String userName, @RequestParam String secId);

    @PostMapping("/issuer/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                             @RequestParam String secId);

}
