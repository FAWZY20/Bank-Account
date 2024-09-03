package com.BankAccount.Bank.Account.Applications;

import com.BankAccount.Bank.Account.Domain.model.Decouvert;
import com.BankAccount.Bank.Account.Domain.model.Livret;
import com.BankAccount.Bank.Account.Domain.model.Operation;
import com.BankAccount.Bank.Account.Infrastructure.api.LivretControler;
import com.BankAccount.Bank.Account.Infrastructure.persistence.DecouvertRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.LivretRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivretService implements LivretControler {

    @Autowired
    LivretRepository livretRepository;

    @Autowired
    DecouvertRepository decouvertRepository;

    @Autowired
    OperationService operationService;

    @Override
    public Boolean ajoutLivret(Livret livret) {
        Decouvert decouvert = decouvertRepository.findDecouvertByNumeroCompte(livret.getNumeroCompte());
        if (livretRepository.findLivretByNumeroCompte(livret.getNumeroCompte()) == null &&
                decouvert.getAutorisation() == false){
            livretRepository.save(livret);
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public void supLivret(String numeroCompte) {
        livretRepository.deleteByNumeroCompte(numeroCompte);
    }

    @Override
    public Boolean depotLivre(String numeroCompte, int argentDeposer) {
        Livret livret = livretRepository.findLivretByNumeroCompte(numeroCompte);
        int depotTotal = argentDeposer + livret.getSolde();
        if ( depotTotal  <= livret.getPlafond()){
            operationService.ajoutOperation(numeroCompte,depotTotal, Operation.TypeOperation.DEPOT, Operation.TypeCompte.LIVRET);
            livret.setSolde(depotTotal);
            livretRepository.save(livret);
            return true;
        }else {
            return false;
        }
    }
}
