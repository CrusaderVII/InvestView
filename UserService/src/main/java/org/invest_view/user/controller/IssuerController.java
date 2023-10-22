package org.invest_view.user.controller;

import org.invest_view.user.model.IssuerData;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user-service/v1/issuer")
public class IssuerController {

    @Autowired
    UserService service;

    @PostMapping("/save/all")
    public void saveAllIssuers() {
        service.saveAllIssuers();
    }

    @GetMapping("/get/all")
    public List<IssuerData> getAllIssuers() {
        return service.getAllIssuers();
    }
}
