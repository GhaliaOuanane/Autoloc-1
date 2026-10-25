package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;
import java.util.Optional;

public interface IEquipementService {
    Equipement creerEquipement(Equipement equipement);
    Equipement modifierEquipement(Equipement equipement);
    void supprimerEquipement(Long idEquipement);
    Optional<Equipement> obtenirEquipementParId(Long idEquipement);
    List<Equipement> obtenirTousLesEquipements();
    long compterEquipements();
}
