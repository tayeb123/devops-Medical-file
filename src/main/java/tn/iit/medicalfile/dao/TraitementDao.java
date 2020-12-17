package tn.iit.medicalfile.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.medicalfile.models.Traitement;
@Repository
public interface TraitementDao extends JpaRepository<Traitement,Long> {
}
