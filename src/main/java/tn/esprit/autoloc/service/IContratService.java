package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;
import java.util.Optional;

public interface IContratService {
    Contrat creerContrat(Contrat contrat);
    Contrat modifierContrat(Contrat contrat);
    void supprimerContrat(Long idContrat);
    Optional<Contrat> obtenirContratParId(Long idContrat);
    List<Contrat> obtenirTousLesContrats();
    long compterContrats();
}
