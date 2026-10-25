package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence creerAgence(Agence agence) {
        log.info("Création d'une nouvelle agence: {}", agence.getNom());
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        log.info("Modification de l'agence avec ID: {}", agence.getIdAgence());
        return agenceRepository.save(agence);
    }

    @Override
    public void supprimerAgence(Long idAgence) {
        log.info("Suppression de l'agence avec ID: {}", idAgence);
        agenceRepository.deleteById(idAgence);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agence> obtenirAgenceParId(Long idAgence) {
        return agenceRepository.findById(idAgence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> obtenirToutesLesAgences() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterAgences() {
        return agenceRepository.count();
    }
}
