package com.example.banque.dto; // Assurez-vous d'utiliser le bon package

import lombok.Data;
import java.math.BigDecimal;

@Data // Fournit getters, setters, toString, etc. (via Lombok)
public class DepotRequest {
    private Long compteId;
    private BigDecimal montant;
    private String libelle; // (Optionnel, mais utile pour la transaction)
}