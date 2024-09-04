package com.BankAccount.Bank.Account.Applications;

import com.BankAccount.Bank.Account.Domain.model.Decouvert;
import com.BankAccount.Bank.Account.Infrastructure.api.DecouvertControler;
import com.BankAccount.Bank.Account.Infrastructure.persistence.CompteBancaireRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.DecouvertRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.LivretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DecouvertService implements DecouvertControler {

    @Autowired
    DecouvertRepository decouvertRepository;

    @Autowired
    CompteBancaireRepository compteBancaireRepository;

    @Autowired
    LivretRepository livretRepository;

    @Override
    public ResponseEntity<Boolean> modifierSoldeDecouvert(String nCompte, int solde) {
        try {
            Decouvert dct = decouvertRepository.findDecouvertByNumeroCompte(nCompte);
            dct.setSoldeDecouvert(solde);
            decouvertRepository.save(dct);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> modifierAutorizationDecouvert(String nCompte, boolean depotArgent) {
        Decouvert dct = decouvertRepository.findDecouvertByNumeroCompte(nCompte);
        if (livretRepository.findLivretByNumeroCompte(nCompte) == null){
            dct.setAutorisation(depotArgent);
            decouvertRepository.save(dct);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
