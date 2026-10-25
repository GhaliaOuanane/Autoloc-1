package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;
import java.util.Optional;

public interface IPaiementService {
    Paiement creerPaiement(Paiement paiement);
    Paiement modifierPaiement(Paiement paiement);
    void supprimerPaiement(Long idPaiement);
    Optional<Paiement> obtenirPaiementParId(Long idPaiement);
    List<Paiement> obtenirTousLesPaiements();
    long compterPaiements();
}
