package org.invest_view.user.repository;

import org.invest_view.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByName(String name);

    User findByEmail(String email);

    void deleteByName(String name);
}
