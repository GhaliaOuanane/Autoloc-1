package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement creerEquipement(Equipement equipement) {
        log.info("Création d'un nouvel équipement: {}", equipement.getLibelle());
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement modifierEquipement(Equipement equipement) {
        log.info("Modification de l'équipement avec ID: {}", equipement.getIdEquipement());
        return equipementRepository.save(equipement);
    }

    @Override
    public void supprimerEquipement(Long idEquipement) {
        log.info("Suppression de l'équipement avec ID: {}", idEquipement);
        equipementRepository.deleteById(idEquipement);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Equipement> obtenirEquipementParId(Long idEquipement) {
        return equipementRepository.findById(idEquipement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> obtenirTousLesEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterEquipements() {
        return equipementRepository.count();
    }
}
