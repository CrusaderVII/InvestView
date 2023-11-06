package org.invest_view.user.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("api/user-service/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/auth")
    public User authenticationUser(@RequestParam String email, @RequestParam String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user, Errors errors) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("/issuers")
    public List<IssuerData> getUsersBookmarks(@RequestParam String userName) {

        return userService.getUserByName(userName)
                .getIssuersData();
    }

    @DeleteMapping("/delete")
    public User deleteUserByName(@RequestParam String userName) {
        return userService.deleteUserByName(userName);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().stream()
                .forEach(error -> {
                    errors.put(error.getRootBean().toString(), error.getMessage());
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlExceptionHandler(SQLException e) {
        // TODO: implement handler for situation, when the name of newly created user is already exists in db
        return null;
    }

}
