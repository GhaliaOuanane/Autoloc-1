package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    // Opérations CRUD
    Client creerClient(Client client);
    Client modifierClient(Client client);
    void supprimerClient(Long idClient);
    Optional<Client> obtenirClientParId(Long idClient);
    List<Client> obtenirTousLesClients();
    
    // Opérations métier
    Optional<Client> obtenirClientParEmail(String email);
    Optional<Client> obtenirClientParNumPermis(String numPermis);
    boolean clientExisteParEmail(String email);
    long compterClients();
}
