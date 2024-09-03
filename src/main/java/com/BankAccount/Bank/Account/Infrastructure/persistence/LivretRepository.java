package com.BankAccount.Bank.Account.Infrastructure.persistence;

import com.BankAccount.Bank.Account.Domain.model.Livret;
import com.BankAccount.Bank.Account.Domain.model.Livret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivretRepository extends JpaRepository<Livret, Long> {


    Livret findLivretByNumeroCompte(String numeroCompte);

    void deleteByNumeroCompte(String numeroCompte);
}
