package com.BankAccount.Bank.Account.Infrastructure.api;

import com.BankAccount.Bank.Account.Domain.model.Livret;
import org.springframework.web.bind.annotation.*;

@RestController
public interface LivretControler {

    @PostMapping("/ajoutLivret")
    public Boolean ajoutLivret(@RequestBody Livret livret);

    @DeleteMapping("/supLivret")
    public void supLivret(@RequestParam("numeroCompte") String numeroCompte);

    @PutMapping("/depotLivre")
    public Boolean depotLivre(@RequestParam("numeroCompte") String numeroCompte,
                           @RequestParam("argentDeposer") int argentDeposer);

}
