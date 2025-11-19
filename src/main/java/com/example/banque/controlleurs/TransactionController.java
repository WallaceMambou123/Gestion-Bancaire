package com.example.banque.controlleurs;

import com.example.banque.Entity.Transaction;
import com.example.banque.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/depot")
    @ResponseStatus(HttpStatus.OK)
    public void depot(
            @RequestParam Long compteId,
            @RequestParam BigDecimal montant,
            @RequestParam(defaultValue = "Dépôt") String libelle) {
        transactionService.deposer(compteId, montant, libelle);
    }

    @PostMapping("/retrait")
    @ResponseStatus(HttpStatus.OK)
    public void retrait(
            @RequestParam Long compteId,
            @RequestParam BigDecimal montant,
            @RequestParam(defaultValue = "Retrait") String libelle) {
        transactionService.retirer(compteId, montant, libelle);
    }

    @PostMapping("/virement")
    @ResponseStatus(HttpStatus.OK)
    public void virement(
            @RequestParam Long source,
            @RequestParam Long destination,
            @RequestParam BigDecimal montant,
            @RequestParam(defaultValue = "Virement") String libelle) {
        transactionService.virement(source, destination, montant, libelle);
    }

    @GetMapping("/releve/{compteId}")
    public List<Transaction> releve(@PathVariable Long compteId) {
        return transactionService.historique(compteId);
    }
}