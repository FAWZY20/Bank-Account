package com.BankAccount.Bank.Account.Infrastructure.api;

import org.springframework.web.bind.annotation.*;

@RestController
public interface DecouvertControler {


    @PutMapping("/modifierSoldeDecouvert")
    public void modifierSoldeDecouvert(@RequestParam("numeroCompte") String nCompte, @RequestParam("solde") int solde);

    @PutMapping("/modifierAutorizationDecouvert")
    public Boolean modifierAutorizationDecouvert(@RequestParam("numeroCompte") String nCompte, @RequestParam("authorization") boolean depotArgent);
}
