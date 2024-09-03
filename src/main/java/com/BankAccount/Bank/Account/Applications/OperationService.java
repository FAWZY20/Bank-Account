package com.BankAccount.Bank.Account.Applications;

import com.BankAccount.Bank.Account.Domain.model.Operation;
import com.BankAccount.Bank.Account.Infrastructure.persistence.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OperationService {

    @Autowired
    OperationRepository operationRepository;

    public void ajoutOperation(String numeroCompte, int montant, Operation.TypeOperation typeOperation , Operation.TypeCompte typeCompte){
        Date date = new Date();
        Operation operation = new Operation();
        operation.setNumeroCompte(numeroCompte);
        operation.setTypeOperation(typeOperation);
        operation.setTypeCompte(typeCompte);
        operation.setMontant(montant);
        operation.setDateOperation(date);
        operationRepository.save(operation);
    }
}
