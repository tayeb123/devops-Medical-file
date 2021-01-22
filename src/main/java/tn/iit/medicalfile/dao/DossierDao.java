package tn.iit.medicalfile.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.medicalfile.domain.Dossier;

@Repository
public interface DossierDao extends JpaRepository<Dossier,Long> {
}
