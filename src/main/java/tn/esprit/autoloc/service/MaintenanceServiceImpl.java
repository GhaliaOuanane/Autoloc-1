package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance creerMaintenance(Maintenance maintenance) {
        log.info("Création d'une nouvelle maintenance");
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifierMaintenance(Maintenance maintenance) {
        log.info("Modification de la maintenance avec ID: {}", maintenance.getIdMaintenance());
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void supprimerMaintenance(Long idMaintenance) {
        log.info("Suppression de la maintenance avec ID: {}", idMaintenance);
        maintenanceRepository.deleteById(idMaintenance);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Maintenance> obtenirMaintenanceParId(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> obtenirToutesLesMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterMaintenances() {
        return maintenanceRepository.count();
    }
}
