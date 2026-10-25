package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe creerEmploye(Employe employe) {
        log.info("Création d'un nouvel employé: {} {}", employe.getNom(), employe.getPrenom());
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifierEmploye(Employe employe) {
        log.info("Modification de l'employé avec ID: {}", employe.getIdEmploye());
        return employeRepository.save(employe);
    }

    @Override
    public void supprimerEmploye(Long idEmploye) {
        log.info("Suppression de l'employé avec ID: {}", idEmploye);
        employeRepository.deleteById(idEmploye);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employe> obtenirEmployeParId(Long idEmploye) {
        return employeRepository.findById(idEmploye);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> obtenirTousLesEmployes() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterEmployes() {
        return employeRepository.count();
    }
}
