package com.BankAccount.Bank.Account.Applications;

import com.BankAccount.Bank.Account.Domain.model.Decouvert;
import com.BankAccount.Bank.Account.Domain.model.Livret;
import com.BankAccount.Bank.Account.Domain.model.Operation;
import com.BankAccount.Bank.Account.Infrastructure.api.LivretControler;
import com.BankAccount.Bank.Account.Infrastructure.persistence.DecouvertRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.LivretRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> ajoutLivret(Livret livret) {
        Decouvert decouvert = decouvertRepository.findDecouvertByNumeroCompte(livret.getNumeroCompte());
        if (livretRepository.findLivretByNumeroCompte(livret.getNumeroCompte()) == null &&
                decouvert.getAutorisation() == false){
            livretRepository.save(livret);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> supLivret(String numeroCompte) {
        try {
            livretRepository.deleteByNumeroCompte(numeroCompte);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public  ResponseEntity<Boolean> depotLivre(String numeroCompte, int argentDeposer) {
        Livret livret = livretRepository.findLivretByNumeroCompte(numeroCompte);
        int depotTotal = argentDeposer + livret.getSolde();
        if ( depotTotal  <= livret.getPlafond()){
            operationService.ajoutOperation(numeroCompte,depotTotal, Operation.TypeOperation.DEPOT, Operation.TypeCompte.LIVRET);
            livret.setSolde(depotTotal);
            livretRepository.save(livret);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
