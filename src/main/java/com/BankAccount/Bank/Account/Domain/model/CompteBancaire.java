package com.BankAccount.Bank.Account.Domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "compte")
public class CompteBancaire {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "compte_seq", allocationSize = 1)
    private UUID id;

    @Column(name = "numerocompte")
    private String numeroCompte;
    @Column(name = "solde", columnDefinition = "0")
    private int solde;

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
