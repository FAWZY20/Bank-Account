package com.BankAccount.Bank.Account.Domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "compte")
public class CompteBancaire {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "compte_seq", allocationSize = 1)
    public Long id;

    @Column(name = "numerocompte")
    public String numeroCompte;
    @Column(name = "solde", columnDefinition = "0")
    public int solde;

    public CompteBancaire(String numeroCompte, int solde) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
    }

    public CompteBancaire(){

    }

    public CompteBancaire(int solde) {
        this.solde = solde;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

}
