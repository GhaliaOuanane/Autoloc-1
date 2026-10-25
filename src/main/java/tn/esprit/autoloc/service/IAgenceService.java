package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;
import java.util.Optional;

public interface IAgenceService {
    Agence creerAgence(Agence agence);
    Agence modifierAgence(Agence agence);
    void supprimerAgence(Long idAgence);
    Optional<Agence> obtenirAgenceParId(Long idAgence);
    List<Agence> obtenirToutesLesAgences();
    long compterAgences();
}
