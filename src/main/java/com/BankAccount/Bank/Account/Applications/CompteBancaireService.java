package com.BankAccount.Bank.Account.Applications;

import com.BankAccount.Bank.Account.Domain.model.CompteBancaire;
import com.BankAccount.Bank.Account.Domain.model.Decouvert;
import com.BankAccount.Bank.Account.Domain.model.Operation;
import com.BankAccount.Bank.Account.Infrastructure.api.CompteBancaireControler;
import com.BankAccount.Bank.Account.Infrastructure.persistence.CompteBancaireRepository;
import com.BankAccount.Bank.Account.Infrastructure.persistence.DecouvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CompteBancaireService implements CompteBancaireControler {

    @Autowired
    CompteBancaireRepository compteBancaireRepository;

    @Autowired
    DecouvertRepository decouvertRepository;

    @Autowired
    OperationService operationService;

    CompteBancaire compteBancaire;

    @Override
    public void ajoutCompte(CompteBancaire cpBancaire) {
        Decouvert decouvert = new Decouvert();
        String numeroCompte;

        //Vérifiez si le numéro de compte est disponible, et si c'est le cas, faisons la boucle jusqu'à ce qu'il ne soit pas disponible.
        do {
            numeroCompte = genererNumeroCompte();
        }while (compteBancaireRepository.findCompteBancaireByNumeroCompte(numeroCompte) != null);

        cpBancaire.setNumeroCompte(numeroCompte);
        compteBancaireRepository.save(cpBancaire);

        decouvert.setNumeroCompte(numeroCompte);
        decouvertRepository.save(decouvert);
    }

    @Override
    public Boolean retrait(@RequestParam("numeroCompte") String numeroCompte,
                           @RequestParam("argentRetrais") int argentRetrais){
        compteBancaire = compteBancaireRepository.findCompteBancaireByNumeroCompte(numeroCompte);
        Decouvert decouvert = decouvertRepository.findDecouvertByNumeroCompte(numeroCompte);

        if (compteBancaire.getSolde() >= argentRetrais){
            operationService.ajoutOperation(numeroCompte,argentRetrais, Operation.TypeOperation.RETRAIT, Operation.TypeCompte.COURANT);
            compteBancaire.setSolde(compteBancaire.getSolde() - argentRetrais);
            compteBancaireRepository.save(compteBancaire);
            return true;
        } else if (decouvert.getAutorisation() == true) {
            //quand il rest un peut d'argent dans le compte courant pour calculer le decouvert et le compte courant ce qu'il va y rester
            if ((decouvert.getSoldeDecouvert() + compteBancaire.getSolde()) >= argentRetrais){
                operationService.ajoutOperation(numeroCompte,argentRetrais, Operation.TypeOperation.RETRAIT, Operation.TypeCompte.COURANT);

                decouvert.setSoldeDecouvert(decouvert.getSoldeDecouvert() - (argentRetrais - compteBancaire.getSolde()));
                compteBancaire.setSolde(compteBancaire.getSolde() - argentRetrais);
                compteBancaireRepository.save(compteBancaire);

                decouvertRepository.save(decouvert);
                return true;
            } else if (decouvert.getSoldeDecouvert() >= argentRetrais) {
                //si le compte courant et a 0
                operationService.ajoutOperation(numeroCompte,argentRetrais, Operation.TypeOperation.RETRAIT, Operation.TypeCompte.COURANT);

                decouvert.setSoldeDecouvert(decouvert.getSoldeDecouvert() - argentRetrais);
                compteBancaire.setSolde(compteBancaire.getSolde() - argentRetrais);
                compteBancaireRepository.save(compteBancaire);

                decouvertRepository.save(decouvert);
                return true;
            }
        }

        return false;
    }

    @Override
    public void depot(@RequestParam("numeroCompte") String numeroCompte,
                      @RequestParam("argentDeposer") int argentDeposer){
        compteBancaire = compteBancaireRepository.findCompteBancaireByNumeroCompte(numeroCompte);
        operationService.ajoutOperation(numeroCompte,argentDeposer, Operation.TypeOperation.DEPOT, Operation.TypeCompte.COURANT);
        compteBancaire.setSolde(compteBancaire.getSolde() + argentDeposer);
        compteBancaireRepository.save(compteBancaire);
    }


    //function qui permet de cree un numero de compte de 27 caractere
    public String genererNumeroCompte(){
        List<Integer> numero = new ArrayList();
        Random random = new Random();
        for (int i = 0 ; i <= 24; i++){
            numero.add(random.nextInt(10));
        }
        String numeroString = numero.stream().map(Object::toString)
                .collect(Collectors.joining(""));

        return "FR" + numeroString;
    }

}
