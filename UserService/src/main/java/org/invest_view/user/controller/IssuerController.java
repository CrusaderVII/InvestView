package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.repository.service.CloudService;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/user-service/v1/issuer")
public class IssuerController {

    @Autowired
    UserService service;
    @Autowired
    CloudService cloudService;

    @PostMapping("save/issuer")
    public void addIssuerToUser(@RequestParam String userName,
                                @RequestParam String secId) {
        service.saveIssuerToUser(userName, secId);
    }

    @PostMapping("/save/all")
    public void saveAllIssuers() {
        service.saveAllIssuers();
    }

    @GetMapping("/get/all")
    public List<IssuerData> getAllIssuers() {
        return service.getAllIssuers();
    }

    @DeleteMapping("/delete/issuer")
    public void deleteIssuerFromUser(@RequestParam String userName, @RequestParam String secId) {
        service.deleteIssuerFromUser(userName, secId);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                             @RequestParam String secId) {
        String imageId = cloudService.uploadFile(file, secId);

        IssuerData issuer = service.getIssuerDataBySecId(secId);
        issuer.setImageId(imageId);
        service.saveIssuer(issuer);

        return new ResponseEntity<>(String.format("Image uploaded for %s company", secId), HttpStatus.OK);
    }
}
