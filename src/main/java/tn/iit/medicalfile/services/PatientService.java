package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.PatientDao;
import tn.iit.medicalfile.domain.Patient;
import tn.iit.medicalfile.dto.PatientDto;
import tn.iit.medicalfile.factory.PatientFactory;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
@Transactional
@Service
public class PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public PatientDto save(PatientDto patientDto) {

        this.patientDao.saveAndFlush (PatientFactory.patientDtoToPatient (patientDto));
        return patientDto;
    }

    public void deleteById(Long id) {
        this.patientDao.deleteById (id);
    }

    public PatientDto findOne(Long id) {
        return PatientFactory.patientToPatientDto (this.patientDao.getOne (id));
    }

    public Collection<PatientDto> findAll() {
        return PatientFactory.patientsToPatientDtos (this.patientDao.findAll ());
    }
}
