package com.example.banque.controlleurs;



import com.example.banque.Entity.Compte;
import com.example.banque.dto.CompteRequest;
import com.example.banque.services.CompteService;
import jakarta.validation.Valid; // Import pour l'annotation @Valid
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    // POST /api/comptes
    @PostMapping
    public Compte ouvrir(@Valid @RequestBody CompteRequest request) { // Ajout de @Valid
        // L'appel au getter a été mis à jour pour correspondre au nouveau nom de champ dans la DTO
        return compteService.ouvrirCompte(
                request.getClientId(), // Changé de getClient_id() à getClientId()
                request.getTypeCompte(),
                request.getSoldeInitial()
        );
    }


    @GetMapping("/client/{client_id}")
    public List<Compte> duClient(@PathVariable Long client_id) {
        return compteService.listerComptesDuClient(client_id);
    }

    @GetMapping("/{compteId}/solde")
    public BigDecimal solde(@PathVariable Long compteId) {
        return compteService.consulterSolde(compteId);
    }
}