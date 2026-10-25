package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client creerClient(Client client) {
        log.info("Création d'un nouveau client avec email: {}", client.getEmail());
        
        // Vérifier si l'email existe déjà
        if (clientRepository.existsByEmail(client.getEmail())) {
            log.error("L'email {} existe déjà", client.getEmail());
            throw new IllegalArgumentException("Un client avec cet email existe déjà");
        }
        
        Client clientCree = clientRepository.save(client);
        log.info("Client créé avec succès avec l'ID: {}", clientCree.getIdClient());
        return clientCree;
    }

    @Override
    public Client modifierClient(Client client) {
        log.info("Modification du client avec ID: {}", client.getIdClient());
        
        if (client.getIdClient() == null) {
            throw new IllegalArgumentException("L'ID du client ne peut pas être null");
        }
        
        if (!clientRepository.existsById(client.getIdClient())) {
            log.error("Client avec ID {} introuvable", client.getIdClient());
            throw new IllegalArgumentException("Client introuvable avec l'ID: " + client.getIdClient());
        }
        
        Client clientModifie = clientRepository.save(client);
        log.info("Client modifié avec succès");
        return clientModifie;
    }

    @Override
    public void supprimerClient(Long idClient) {
        log.info("Suppression du client avec ID: {}", idClient);
        
        if (!clientRepository.existsById(idClient)) {
            log.error("Client avec ID {} introuvable", idClient);
            throw new IllegalArgumentException("Client introuvable avec l'ID: " + idClient);
        }
        
        clientRepository.deleteById(idClient);
        log.info("Client supprimé avec succès");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> obtenirClientParId(Long idClient) {
        log.info("Recherche du client avec ID: {}", idClient);
        return clientRepository.findById(idClient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> obtenirTousLesClients() {
        log.info("Récupération de tous les clients");
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> obtenirClientParEmail(String email) {
        log.info("Recherche du client avec email: {}", email);
        return clientRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> obtenirClientParNumPermis(String numPermis) {
        log.info("Recherche du client avec numéro de permis: {}", numPermis);
        return clientRepository.findByNumPermis(numPermis);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean clientExisteParEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public long compterClients() {
        long count = clientRepository.count();
        log.info("Nombre total de clients: {}", count);
        return count;
    }
}
