package com.example.banque.repository;

import com.example.banque.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Trouve toutes les transactions pour un ID de compte donné, triées par date de transaction décroissante.
    List<Transaction> findByCompteIdOrderByDateTransactionDesc(Long compteId);
}