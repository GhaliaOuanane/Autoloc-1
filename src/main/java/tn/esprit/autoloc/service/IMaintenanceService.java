package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {
    Maintenance creerMaintenance(Maintenance maintenance);
    Maintenance modifierMaintenance(Maintenance maintenance);
    void supprimerMaintenance(Long idMaintenance);
    Optional<Maintenance> obtenirMaintenanceParId(Long idMaintenance);
    List<Maintenance> obtenirToutesLesMaintenances();
    long compterMaintenances();
}
