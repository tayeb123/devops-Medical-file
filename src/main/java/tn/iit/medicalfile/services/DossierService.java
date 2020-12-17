package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.DossierDao;
import tn.iit.medicalfile.models.Dossier;

import java.util.List;

@Service
public class DossierService {
    private final DossierDao dossierDao;

    @Autowired
    public DossierService(DossierDao dossierDao) {
        this.dossierDao = dossierDao;
    }

    public Dossier save(Dossier dossier) {
        return this.dossierDao.saveAndFlush (dossier);
    }

    public void deleteById(Long id) {
        this.dossierDao.deleteById (id);
    }

    public Dossier findOne(Long id) {
        return this.dossierDao.getOne (id);
    }

    public List<Dossier> findAll() {
        return this.dossierDao.findAll ();
    }
}
