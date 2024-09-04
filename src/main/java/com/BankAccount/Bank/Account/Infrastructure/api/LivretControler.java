package com.BankAccount.Bank.Account.Infrastructure.api;

import com.BankAccount.Bank.Account.Domain.model.Livret;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface LivretControler {

    @PostMapping("/postLivret")
    ResponseEntity<Boolean> ajoutLivret(@RequestBody Livret livret);

    @DeleteMapping("/deleteLivret")
    ResponseEntity<Boolean> supLivret(@RequestParam("numeroCompte") String numeroCompte);

    @PatchMapping("/depotLivre")
    ResponseEntity<Boolean> depotLivre(@RequestParam("numeroCompte") String numeroCompte,
                           @RequestParam("argentDeposer") int argentDeposer);

}
