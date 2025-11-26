package com.example.banque.controlleurs;



import com.example.banque.Entity.Compte;
import com.example.banque.dto.CompteRequest;
import com.example.banque.services.CompteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {
//Souffle du controlleur, deuxieme mouvement CompteBanquaire
//    DTO..... quatrieme forme
    private final CompteService compteService;

    // Creation du complte
    @PostMapping
    public Compte ouvrir(@Valid @RequestBody CompteRequest request) {

        return compteService.ouvrirCompte(
                request.getClientId(),
                request.getTypeCompte(),
                request.getSoldeInitial()
        );
    }

// Recupere le compte via l'id
    @GetMapping("/client/{client_id}")
    public List<Compte> duClient(@PathVariable Long client_id) {
        return compteService.listerComptesDuClient(client_id);
    }
//COnsiulte le solde via id
    @GetMapping("/{compteId}/solde")
    public BigDecimal solde(@PathVariable Long compteId) {
        return compteService.consulterSolde(compteId);
    }
}