package com.example.banque.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CompteRequest {


    @NotNull(message = "L'ID du client est obligatoire pour ouvrir un compte.")
    @Min(value = 1, message = "L'ID du client doit être superieur à zero.")
    @JsonProperty("client_id")
    private Long clientId;

    @NotNull(message = "Le type de compte est obligatoire.")
    @NotBlank(message = "Le type de compte ne peut être vide.")
    private String typeCompte;

    private BigDecimal soldeInitial;
}