package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Client;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<Client, Long> {
    // Méthodes de recherche personnalisées
    Optional<Client> findByEmail(String email);
    Optional<Client> findByNumPermis(String numPermis);
    boolean existsByEmail(String email);
}
