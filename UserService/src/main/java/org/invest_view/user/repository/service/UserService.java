package org.invest_view.user.repository.service;
import org.invest_view.user.model.IssuerData;
import org.invest_view.user.model.User;
import org.invest_view.user.repository.IssuerRepository;
import org.invest_view.user.repository.service.request.RequestConstructor;
import org.invest_view.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    IssuerRepository issuerRepository;

    public UserService() {

    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
        if (user==null) user = new User("not found", "", "");

        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user==null) user = new User("not found", "", "");

        return user;
    }

    public User getUserByName(String name) {
        User user = userRepository.findByName(name);
        if(user==null) user = new User("not found", "", "");

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
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

    public List<IssuerData> getAllIssuers() {
        return issuerRepository.findAll();
    }
}
