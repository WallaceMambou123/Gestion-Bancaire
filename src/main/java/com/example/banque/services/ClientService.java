package com.example.banque.services;

import com.example.banque.Entity.Client;
import com.example.banque.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClientService {

    private final ClientRepository clientRepository;


    public Client creerClient(String nom, String prenom, String email, String telephone) {
        Client client = new Client();
        client.setName(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setTelephone(telephone);
        return clientRepository.save(client);
    }


    public List<Client> listerTousLesClients() {
        return clientRepository.findAll();
    }


    public Client trouverParId(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client pas trouve avec l'id : " + id));
    }


    @Transactional
    public void supprimerClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client pas trouve");
        }
        clientRepository.deleteById(id);
    }
}