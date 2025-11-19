package com.example.banque.services;

import com.example.banque.Entity.Compte;
import com.example.banque.Entity.Transaction;
import com.example.banque.repository.CompteRepository;
import com.example.banque.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public void deposer(Long compteId, BigDecimal montant, String libelle) {
        valideMontant(montant);
        Compte compte = getCompte(compteId);

        compte.crediter(montant);
        creerTransaction(compte, montant, "CREDIT", libelle + " (dépôt)");
    }

    @Transactional
    public void retirer(Long compteId, BigDecimal montant, String libelle) {
        valideMontant(montant);
        Compte compte = getCompte(compteId);

        if (compte.getSolde().compareTo(montant) < 0) {
            throw new IllegalArgumentException("Solde insuffisant");
        }

        compte.debiter(montant);
        creerTransaction(compte, montant, "DEBIT", libelle + " (retrait)");
    }

    @Transactional
    public void virement(Long sourceId, Long destinationId, BigDecimal montant, String libelle) {
        if (sourceId.equals(destinationId)) {
            throw new IllegalArgumentException("Impossible de virer sur le même compte");
        }

        retirer(sourceId, montant, libelle);
        deposer(destinationId, montant, libelle);
    }

    public List<Transaction> historique(Long compteId) {
        return transactionRepository.findByCompteIdOrderByDateTransactionDesc(compteId);
    }

    // Méthodes privées utilitaires
    private Compte getCompte(Long compteId) {
        return compteRepository.findById(compteId)
                .orElseThrow(() -> new EntityNotFoundException("Compte non trouvé : " + compteId));
    }

    private void creerTransaction(Compte compte, BigDecimal montant, String type, String description) {
        Transaction tx = new Transaction();
        tx.setMontant(montant);
        tx.setTypeTransaction(type);
        tx.setDescription(description);
        tx.setDateTransaction(LocalDateTime.now());
        tx.setCompte(compte);
        transactionRepository.save(tx);
    }

    private void valideMontant(BigDecimal montant) {
        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
    }
}