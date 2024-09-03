package com.BankAccount.Bank.Account.Domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livret")
public class Livret {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "livret_seq", allocationSize = 1)
    public Long id;

    @Column(name = "numerocompte")
    public String numeroCompte;

    @Column(name = "solde", columnDefinition = "0")
    public int solde;
    @Column(name = "plafond", columnDefinition = "0")
    public int plafond;

    public Livret(String numeroCompte, int solde, int plafond) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.plafond = plafond;
    }

    public Livret(){

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

    public int getPlafond() {
        return plafond;
    }

    public void setPlafond(int plafond) {
        this.plafond = plafond;
    }

}
