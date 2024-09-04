package com.BankAccount.Bank.Account.Domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "decouvert")
public class Decouvert {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "decouvert_seq", allocationSize = 1)
    private UUID id;

    @Column(name = "numerocompte")
    private String numeroCompte;

    @Column(name = "autorisation")
    private Boolean autorisation = false ;

    @Column(name = "soldedecouvert", columnDefinition = "0")
    private int soldeDecouvert;

    public Decouvert(String numeroCompte, Boolean autorisation, int soldeDecouvert) {
        this.numeroCompte = numeroCompte;
        this.autorisation = autorisation;
        this.soldeDecouvert = soldeDecouvert;
    }

    public Decouvert(){

    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    public int getSoldeDecouvert() {
        return soldeDecouvert;
    }

    public void setSoldeDecouvert(int soldeDecouvert) {
        this.soldeDecouvert = soldeDecouvert;
    }

}
