package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {
    // Opérations CRUD
    Vehicule creerVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Vehicule vehicule);
    void supprimerVehicule(Long idVehicule);
    Optional<Vehicule> obtenirVehiculeParId(Long idVehicule);
    List<Vehicule> obtenirTousLesVehicules();
    
    // Opérations métier
    Optional<Vehicule> obtenirVehiculeParImmatriculation(String immatriculation);
    List<Vehicule> obtenirVehiculesParStatut(StatutVehicule statut);
    List<Vehicule> obtenirVehiculesParCategorie(CategorieVehicule categorie);
    List<Vehicule> obtenirVehiculesParMarque(String marque);
    boolean vehiculeExisteParImmatriculation(String immatriculation);
    long compterVehicules();
}
