package com.BankAccount.Bank.Account.Infrastructure.api;


import com.BankAccount.Bank.Account.Domain.model.CompteBancaire;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CompteBancaireControler {


    @PostMapping("/ajoutCompte")
    public void ajoutCompte(@RequestBody CompteBancaire compteBancaire);

    @PutMapping("/retrait")
    public Boolean retrait(@RequestParam("numeroCompte") String numeroCompte,
                           @RequestParam("argentRetrais") int argentRetrais);

    @PutMapping("/depot")
    public void depot(@RequestParam("numeroCompte") String numeroCompte,
                      @RequestParam("argentDeposer") int argentDeposer);
}
