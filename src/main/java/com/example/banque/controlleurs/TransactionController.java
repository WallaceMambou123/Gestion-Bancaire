package com.example.banque.controlleurs;

import com.example.banque.Entity.Transaction;
import com.example.banque.dto.DepotRequest;
import com.example.banque.dto.RetraitRequest;
import com.example.banque.dto.VirementRequest;
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

    // 1. Dépôt : Utilise @RequestBody pour une requête POST plus standard
    @PostMapping("/depot")
    @ResponseStatus(HttpStatus.CREATED) // Statut plus approprié pour la création d'une opération
    public void depot(@RequestBody DepotRequest request) { // Utilise un DTO (Data Transfer Object)
        transactionService.deposer(request.getCompteId(), request.getMontant(), request.getLibelle());
    }

    // 2. Retrait : Utilise @RequestBody pour une requête POST plus standard
    @PostMapping("/retrait")
    @ResponseStatus(HttpStatus.CREATED)
    public void retrait(@RequestBody RetraitRequest request) { // Utilise un DTO
        transactionService.retirer(request.getCompteId(), request.getMontant(), request.getLibelle());
    }

    // 3. Virement : Utilise @RequestBody pour une requête POST plus standard
    @PostMapping("/virement")
    @ResponseStatus(HttpStatus.CREATED)
    public void virement(@RequestBody VirementRequest request) { // Utilise un DTO
        transactionService.virement(
                request.getSource(),
                request.getDestination(),
                request.getMontant(),
                request.getLibelle()
        );
    }

    // 4. Relevé : Laisse le GET inchangé (conventionnel et correct)
    @GetMapping("/releve/{compteId}")
    public List<Transaction> releve(@PathVariable Long compteId) {
        return transactionService.historique(compteId);
    }
}