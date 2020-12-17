package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.PatientDao;
import tn.iit.medicalfile.models.Patient;

import java.util.List;

@Service
public class PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public Patient save(Patient patient) {
        return this.patientDao.saveAndFlush (patient);
    }

    public void deleteById(Long id) {
        this.patientDao.deleteById (id);
    }

    public Patient findOne(Long id) {
        return this.patientDao.getOne (id);
    }

    public List<Patient> findAll() {
        return this.patientDao.findAll ();
    }
}
