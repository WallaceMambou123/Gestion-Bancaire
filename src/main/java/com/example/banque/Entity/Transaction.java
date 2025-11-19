package com.example.banque.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;   // ← IMPORTANT : LocalDateTime, pas LocalDate !

@Entity
@Table(name = "transactions")  // ou "transaction" si tu préfères
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "compte")  // on garde seulement compte ici
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_transaction", nullable = false)
    private LocalDateTime dateTransaction = LocalDateTime.now(); // valeur par défaut

    @Column(name = "type_transaction", nullable = false, length = 20)
    private String typeTransaction; // "DEBIT", "CREDIT", "VIREMENT_EMIS", "VIREMENT_RECU"...

    @Column(length = 255)
    private String description; // optionnel mais très utile

    // RELATION OBLIGATOIRE : une transaction appartient à UN SEUL compte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id", nullable = false)
    @JsonIgnore  // INDISPENSABLE pour éviter la boucle JSON infinie
    private Compte compte;

    // Constructeur pratique (sans id)
    public Transaction(BigDecimal montant, String typeTransaction, String description, Compte compte) {
        this.montant = montant;
        this.typeTransaction = typeTransaction;
        this.description = description;
        this.compte = compte;
        this.dateTransaction = LocalDateTime.now();
    }
}