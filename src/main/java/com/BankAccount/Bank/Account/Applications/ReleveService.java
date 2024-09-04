package com.BankAccount.Bank.Account.Applications;


import com.BankAccount.Bank.Account.Applications.dto.ReleveDTO;
import com.BankAccount.Bank.Account.Domain.model.CompteBancaire;
import com.BankAccount.Bank.Account.Domain.model.Livret;
import com.BankAccount.Bank.Account.Infrastructure.api.ReleveControler;
import com.BankAccount.Bank.Account.Infrastructure.persistence.CompteBancaireRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.LivretRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReleveService implements ReleveControler {

    @Autowired
    CompteBancaireRepository compteBancaireRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    LivretRepository livretRepository;


    @Override
    public ResponseEntity<ReleveDTO> getRelever(String nCompte, String typeCompte) {
        LocalDate dateEmision = LocalDateTime.now().toLocalDate();
        LocalDate dateDebut = dateEmision.minusDays(30);

        ReleveDTO releveDTO = new ReleveDTO();

        CompteBancaire compteBancaire = compteBancaireRepository.findCompteBancaireByNumeroCompte(nCompte);
        Livret livret = livretRepository.findLivretByNumeroCompte(nCompte);

        if (typeCompte.equals("livret")){
            releveDTO.setSolde(livret.getSolde());
        } else {
            releveDTO.setSolde(compteBancaire.getSolde());
        }

        releveDTO.setDateEmission(dateEmision);
        releveDTO.setTypeCompte(typeCompte.toUpperCase());
        releveDTO.setListOperation(operationRepository.findOperationForRelever(nCompte,typeCompte.toUpperCase(),dateDebut,dateEmision));

        return new ResponseEntity<>(releveDTO, HttpStatus.OK);
    }
}
