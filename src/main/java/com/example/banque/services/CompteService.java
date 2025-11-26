package com.example.banque.services;

import com.example.banque.Entity.Compte;
import com.example.banque.Entity.Client;
import com.example.banque.repository.CompteRepository;
import com.example.banque.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;


    public Compte ouvrirCompte(Long clientId, String typeCompte, BigDecimal soldeInitial) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client pas trouver"));

        Compte compte = new Compte();
        compte.setNumeroCompte(genererNumeroCompte());
        compte.setTypeCompte(typeCompte.toUpperCase());
        compte.setSolde(soldeInitial != null ? soldeInitial : BigDecimal.ZERO);
        compte.setDateOuverture(LocalDate.now());
        compte.setClient(client);

        return compteRepository.save(compte);
    }


    public List<Compte> listerComptesDuClient(Long clientId) {
        return compteRepository.findByClientId(clientId);
    }


    public BigDecimal consulterSolde(Long compteId) {
        return compteRepository.findById(compteId)
                .orElseThrow(() -> new EntityNotFoundException("Compte pas trouver"))
                .getSolde();
    }

    // Trouver un compte avec vérification
    public Compte trouverCompteParId(Long compteId) {
        return compteRepository.findById(compteId)
                .orElseThrow(() -> new EntityNotFoundException("Compte pas trouver : " + compteId));
    }

    private String genererNumeroCompte() {
        return "FR76" + System.nanoTime();
    }
}