package tn.iit.medicalfile.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.medicalfile.domain.Patient;
@Repository
public interface PatientDao extends JpaRepository<Patient,Long> {
}
