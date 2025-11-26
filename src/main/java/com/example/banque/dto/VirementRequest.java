package com.example.banque.dto; // Assurez-vous d'utiliser le bon package

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VirementRequest {
    private Long source; // Compte source
    private Long destination; // Compte destination
    private BigDecimal montant;
    private String libelle;
}