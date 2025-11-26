package com.example.banque.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank; // Mieux pour les Strings que NotNull
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CompteRequest {

    // Assure le mapping JSON 'client_id' vers le champ Java 'clientId'
    // Lombok génère getClientId() et setClientId()
    @NotNull(message = "L'ID du client est obligatoire pour ouvrir un compte.")
    @Min(value = 1, message = "L'ID du client doit être supérieur à zéro.")
    @JsonProperty("client_id") // Utilise client_id dans le JSON
    private Long clientId;

    @NotNull(message = "Le type de compte est obligatoire.")
    @NotBlank(message = "Le type de compte ne peut être vide.") // Ajout pour s'assurer que la chaîne n'est pas vide
    private String typeCompte;

    private BigDecimal soldeInitial;
}