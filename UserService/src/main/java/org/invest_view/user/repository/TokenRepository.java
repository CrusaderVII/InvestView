package org.invest_view.user.repository;

import org.invest_view.user.model.ValidationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<ValidationToken, Long> {

    public ValidationToken findByToken(String token);
}
