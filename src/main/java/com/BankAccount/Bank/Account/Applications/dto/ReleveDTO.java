package com.BankAccount.Bank.Account.Applications.dto;

import com.BankAccount.Bank.Account.Domain.model.Operation;
import java.time.LocalDate;
import java.util.List;

public class ReleveDTO {
    public String typeCompte;

    public int solde;

    public LocalDate dateEmission;

    public List<Operation> listOperation;

    public ReleveDTO( String typeCompte, int solde, LocalDate dateEmission, List<Operation> listOperation) {
        this.typeCompte = typeCompte;
        this.solde = solde;
        this.dateEmission = dateEmission;
        this.listOperation = listOperation;
    }

    public ReleveDTO(){

    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public List<Operation> getListOperation() {
        return listOperation;
    }

    public void setListOperation(List<Operation> listOperation) {
        this.listOperation = listOperation;
    }

}
