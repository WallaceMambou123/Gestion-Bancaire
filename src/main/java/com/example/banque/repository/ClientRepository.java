package com.example.banque.repository;

import com.example.banque.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Rien à écrire ici pour l'instant → tu as déjà tout !
}