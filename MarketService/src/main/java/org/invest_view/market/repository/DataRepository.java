package org.invest_view.market.repository;

import org.invest_view.user.model.IssuerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<IssuerData, Long> {
}
