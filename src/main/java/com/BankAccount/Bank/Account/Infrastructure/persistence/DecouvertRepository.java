package com.BankAccount.Bank.Account.Infrastructure.persistence;

import com.BankAccount.Bank.Account.Domain.model.Decouvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecouvertRepository extends JpaRepository<Decouvert, Long> {

    Decouvert findDecouvertByNumeroCompte(String numeroCompte);
}
