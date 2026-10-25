package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule creerVehicule(Vehicule vehicule) {
        log.info("Création d'un nouveau véhicule avec immatriculation: {}", vehicule.getImmatriculation());
        
        // Vérifier si l'immatriculation existe déjà
        if (vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            log.error("L'immatriculation {} existe déjà", vehicule.getImmatriculation());
            throw new IllegalArgumentException("Un véhicule avec cette immatriculation existe déjà");
        }
        
        Vehicule vehiculeCree = vehiculeRepository.save(vehicule);
        log.info("Véhicule créé avec succès avec l'ID: {}", vehiculeCree.getIdVehicule());
        return vehiculeCree;
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        log.info("Modification du véhicule avec ID: {}", vehicule.getIdVehicule());
        
        if (vehicule.getIdVehicule() == null) {
            throw new IllegalArgumentException("L'ID du véhicule ne peut pas être null");
        }
        
        if (!vehiculeRepository.existsById(vehicule.getIdVehicule())) {
            log.error("Véhicule avec ID {} introuvable", vehicule.getIdVehicule());
            throw new IllegalArgumentException("Véhicule introuvable avec l'ID: " + vehicule.getIdVehicule());
        }
        
        Vehicule vehiculeModifie = vehiculeRepository.save(vehicule);
        log.info("Véhicule modifié avec succès");
        return vehiculeModifie;
    }

    @Override
    public void supprimerVehicule(Long idVehicule) {
        log.info("Suppression du véhicule avec ID: {}", idVehicule);
        
        if (!vehiculeRepository.existsById(idVehicule)) {
            log.error("Véhicule avec ID {} introuvable", idVehicule);
            throw new IllegalArgumentException("Véhicule introuvable avec l'ID: " + idVehicule);
        }
        
        vehiculeRepository.deleteById(idVehicule);
        log.info("Véhicule supprimé avec succès");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> obtenirVehiculeParId(Long idVehicule) {
        log.info("Recherche du véhicule avec ID: {}", idVehicule);
        return vehiculeRepository.findById(idVehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> obtenirTousLesVehicules() {
        log.info("Récupération de tous les véhicules");
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> obtenirVehiculeParImmatriculation(String immatriculation) {
        log.info("Recherche du véhicule avec immatriculation: {}", immatriculation);
        return vehiculeRepository.findByImmatriculation(immatriculation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> obtenirVehiculesParStatut(StatutVehicule statut) {
        log.info("Recherche des véhicules avec statut: {}", statut);
        return vehiculeRepository.findByStatut(statut);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> obtenirVehiculesParCategorie(CategorieVehicule categorie) {
        log.info("Recherche des véhicules de catégorie: {}", categorie);
        return vehiculeRepository.findByCategorie(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> obtenirVehiculesParMarque(String marque) {
        log.info("Recherche des véhicules de marque: {}", marque);
        return vehiculeRepository.findByMarque(marque);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean vehiculeExisteParImmatriculation(String immatriculation) {
        return vehiculeRepository.existsByImmatriculation(immatriculation);
    }

    @Override
    @Transactional(readOnly = true)
    public long compterVehicules() {
        long count = vehiculeRepository.count();
        log.info("Nombre total de véhicules: {}", count);
        return count;
    }
}
