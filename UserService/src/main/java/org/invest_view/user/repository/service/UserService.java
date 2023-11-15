package org.invest_view.user.repository.service;
import jakarta.transaction.Transactional;
import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.model.ValidationToken;
import org.invest_view.user.repository.IssuerRepository;
import org.invest_view.user.repository.TokenRepository;
import org.invest_view.user.repository.service.request.RequestConstructor;
import org.invest_view.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IssuerRepository issuerRepository;
    @Autowired
    TokenRepository tokenRepository;

    public UserService() {

    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
        if (user==null) user = new User("not found", "", "");

        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(7);
        User user = getUserByEmail(email);

        if (user==null) return new User("not found", "", "");

        if (encoder.matches(password, user.getPassword())) return user;
        else return new User("not found", "", "");
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User info) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(7);
        String encodedPassword = encoder.encode(info.getPassword());

        User user = new User(info.getName(), info.getEmail(), encodedPassword);
        ValidationToken token = new ValidationToken(user);
        user.setToken(token);

        tokenRepository.save(token);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteUserByName(String name) {
        userRepository.deleteByName(name);
    }

    public IssuerData saveIssuerToUser(String userName, String secId) {
        User user = userRepository.findByName(userName);
        IssuerData issuerData = issuerRepository.findBySecId(secId);
        user.addIssuer(issuerData);

        userRepository.save(user);

        return issuerData;
    }

    public void deleteIssuerFromUser(String userName, String secId) {
        User user = getUserByName(userName);
        IssuerData issuerData = getIssuerDataBySecId(secId);

        user.deleteIssuer(issuerData);

        saveUser(user);
    }

    public IssuerData getIssuerDataBySecId(String secId) {
        return issuerRepository.findBySecId(secId);
    }

    public void saveAllIssuers() {
        RequestConstructor.getAllIssuers().stream()
                .forEach(issuerData -> issuerRepository.save(issuerData));
    }

    public IssuerData saveIssuer(IssuerData issuer) {
        return issuerRepository.save(issuer);
    }

    public List<IssuerData> getAllIssuers() {
        return issuerRepository.findAll();
    }
}
