package com.BankAccount.Bank.Account.Infrastructure.api;


import com.BankAccount.Bank.Account.Domain.model.CompteBancaire;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CompteBancaireControler {


    @PostMapping("/postCompte")
    ResponseEntity<Boolean>  ajoutCompte(@RequestBody CompteBancaire compteBancaire);

    @PatchMapping("/retrait")
    ResponseEntity<Boolean> retrait(@RequestParam("numeroCompte") String numeroCompte,
                           @RequestParam("argentRetrais") int argentRetrais);

    @PatchMapping("/depot")
    ResponseEntity<Boolean> depot(@RequestParam("numeroCompte") String numeroCompte,
                      @RequestParam("argentDeposer") int argentDeposer);
}
