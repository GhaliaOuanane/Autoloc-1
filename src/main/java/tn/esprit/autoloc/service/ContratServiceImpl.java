package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    public Contrat creerContrat(Contrat contrat) {
        log.info("Création d'un nouveau contrat");
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifierContrat(Contrat contrat) {
        log.info("Modification du contrat avec ID: {}", contrat.getIdContrat());
        return contratRepository.save(contrat);
    }

    @Override
    public void supprimerContrat(Long idContrat) {
        log.info("Suppression du contrat avec ID: {}", idContrat);
        contratRepository.deleteById(idContrat);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contrat> obtenirContratParId(Long idContrat) {
        return contratRepository.findById(idContrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> obtenirTousLesContrats() {
        return contratRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterContrats() {
        return contratRepository.count();
    }
}
