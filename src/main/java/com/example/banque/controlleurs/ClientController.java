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

    private final ClientService clientService;

    // POST   /api/clients → créer un client
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

    // GET    /api/clients → tous les clients
    @GetMapping
    public List<Client> lister() {
        return clientService.listerTousLesClients();
    }

    // GET    /api/clients/5 → un client précis
    @GetMapping("/{id}")
    public Client trouver(@PathVariable Long id) {
        return clientService.trouverParId(id);
    }

    // DELETE /api/clients/5 → supprimer
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        clientService.supprimerClient(id);
    }
}