package com.example.banque.controlleurs;



import com.example.banque.Entity.Compte;
import com.example.banque.services.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    // POST /api/comptes?clientId=1&type=COURANT&soldeInitial=1000
    @PostMapping
    public Compte ouvrir(
            @RequestParam Long clientId,
            @RequestParam String type,
            @RequestParam(required = false) BigDecimal soldeInitial) {

        return compteService.ouvrirCompte(clientId, type, soldeInitial);
    }

    @GetMapping("/client/{clientId}")
    public List<Compte> duClient(@PathVariable Long clientId) {
        return compteService.listerComptesDuClient(clientId);
    }

    @GetMapping("/{compteId}/solde")
    public BigDecimal solde(@PathVariable Long compteId) {
        return compteService.consulterSolde(compteId);
    }
}