package org.invest_view.user.repository;

import org.invest_view.user.model.IssuerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuerRepository extends JpaRepository<IssuerData, Long> {

    public IssuerData findBySecId(String secId);
}
