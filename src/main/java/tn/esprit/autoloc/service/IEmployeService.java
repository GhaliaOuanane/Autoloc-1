package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;
import java.util.Optional;

public interface IEmployeService {
    Employe creerEmploye(Employe employe);
    Employe modifierEmploye(Employe employe);
    void supprimerEmploye(Long idEmploye);
    Optional<Employe> obtenirEmployeParId(Long idEmploye);
    List<Employe> obtenirTousLesEmployes();
    long compterEmployes();
}
