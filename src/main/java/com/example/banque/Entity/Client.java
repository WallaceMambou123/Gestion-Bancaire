package com.example.banque.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "Comptes")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column (nullable = false, length = 100)
    private String email;

    @Column (nullable = false, length = 100)
    private String telephone;

    //Pour que le compte client possede plusieurs compte et la relation principale est @OneToMany vers Compte
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compte> comptes;
}
