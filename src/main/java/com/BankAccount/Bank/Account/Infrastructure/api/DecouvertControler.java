package com.BankAccount.Bank.Account.Infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface DecouvertControler {


    @PatchMapping("/patchSoldeDecouvert")
    ResponseEntity<Boolean> modifierSoldeDecouvert(@RequestParam("numeroCompte") String nCompte, @RequestParam("solde") int solde);

    @PutMapping("/patchAutorizationDecouvert")
    ResponseEntity<Boolean> modifierAutorizationDecouvert(@RequestParam("numeroCompte") String nCompte, @RequestParam("authorization") boolean depotArgent);
}
