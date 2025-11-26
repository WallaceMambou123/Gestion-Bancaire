package com.example.banque.dto; // Assurez-vous d'utiliser le bon package

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DepotRequest {
    private Long compteId;
    private BigDecimal montant;
    private String libelle;
}