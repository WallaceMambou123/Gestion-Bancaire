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
//Souffle du controlleur, troisieme mouvement Transaction


    private final TransactionService transactionService;

    // Gestion du depot
    @PostMapping("/depot")
    @ResponseStatus(HttpStatus.CREATED)
//    Ouverture de la porte des DTO, technique secrete
    public void depot(@RequestBody DepotRequest request) {
        transactionService.deposer(request.getCompteId(), request.getMontant(), request.getLibelle());
    }

    // Gestion du retrait
    @PostMapping("/retrait")
    @ResponseStatus(HttpStatus.CREATED)

    public void retrait(@RequestBody RetraitRequest request) {
        transactionService.retirer(request.getCompteId(), request.getMontant(), request.getLibelle());
    }

    // Gestion des virement
    @PostMapping("/virement")
    @ResponseStatus(HttpStatus.CREATED)
    public void virement(@RequestBody VirementRequest request) {
        transactionService.virement(
                request.getSource(),
                request.getDestination(),
                request.getMontant(),
                request.getLibelle()
        );
    }

//    Historique des technique utiliser pour eveiller l'Axe
    @GetMapping("/releve/{compteId}")
    public List<Transaction> releve(@PathVariable Long compteId) {
        return transactionService.historique(compteId);
    }
}