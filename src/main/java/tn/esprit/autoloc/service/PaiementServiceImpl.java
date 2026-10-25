package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement creerPaiement(Paiement paiement) {
        log.info("Création d'un nouveau paiement");
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement modifierPaiement(Paiement paiement) {
        log.info("Modification du paiement avec ID: {}", paiement.getIdPaiement());
        return paiementRepository.save(paiement);
    }

    @Override
    public void supprimerPaiement(Long idPaiement) {
        log.info("Suppression du paiement avec ID: {}", idPaiement);
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paiement> obtenirPaiementParId(Long idPaiement) {
        return paiementRepository.findById(idPaiement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> obtenirTousLesPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterPaiements() {
        return paiementRepository.count();
    }
}
