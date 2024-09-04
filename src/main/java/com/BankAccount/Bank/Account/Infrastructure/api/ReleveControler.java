package com.BankAccount.Bank.Account.Infrastructure.api;

import com.BankAccount.Bank.Account.Applications.dto.ReleveDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ReleveControler {

    @GetMapping("/getRelever")
    ResponseEntity<ReleveDTO> getRelever(@RequestParam("nCompte") String nCompte, @RequestParam("typeCompte") String typeCompte);

}
