package com.BankAccount.Bank.Account.Domain.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "livret_seq", allocationSize = 1)
    private UUID id;

    @Column(name = "numerocompte")
    private String numeroCompte;

    @Column(name = "dateoperation")
    private Date dateOperation;

    @Column(name = "montant")
    private double montant;
    @Enumerated(EnumType.STRING)
    @Column(name = "typeoperation")
    private TypeOperation typeOperation;

    @Enumerated(EnumType.STRING)
    @Column(name = "typecompte")
    private TypeCompte typeCompte;

    public enum TypeCompte{
        LIVRET, COURANT
    }
    public enum TypeOperation{
        DEPOT, RETRAIT
    }

    public Operation(String numeroCompte, Date dateOperation, double montant, TypeOperation typeOperation, TypeCompte typeCompte) {
        this.numeroCompte = numeroCompte;
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.typeOperation = typeOperation;
        this.typeCompte = typeCompte;
    }

    public Operation(){

    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant)                           {
        this.montant = montant;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

}
