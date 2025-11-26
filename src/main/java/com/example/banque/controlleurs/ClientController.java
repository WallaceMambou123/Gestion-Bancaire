package com.example.banque.controlleurs;


import com.example.banque.Entity.Client;
import com.example.banque.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
//Souffle du controlleur, premier mouvement Client de la banque

    private final ClientService clientService;

    // Invocation du client
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client creer(@RequestBody Client client) {
        return clientService.creerClient(
                client.getName(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone()
        );
    }

    // Lister tous les clients
    @GetMapping
    public List<Client> lister() {
        return clientService.listerTousLesClients();
    }

    //Retourner un client via id
    @GetMapping("/{id}")
    public Client trouver(@PathVariable Long id) {
        return clientService.trouverParId(id);
    }

    // Suppression via id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        clientService.supprimerClient(id);
    }
}