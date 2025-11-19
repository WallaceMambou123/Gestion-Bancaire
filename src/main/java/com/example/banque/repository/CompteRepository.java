package com.example.banque.repository;

import com.example.banque.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    // Exemple de méthode personnalisée que tu peux ajouter plus tard
    // List<Compte> findByClientId(Long clientId);
}