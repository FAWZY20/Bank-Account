package com.BankAccount.Bank.Account.Infrastructure.persistence;

import com.BankAccount.Bank.Account.Domain.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {

    CompteBancaire findCompteBancaireByNumeroCompte(String numeroCompte);
}
