package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.TraitementDao;
import tn.iit.medicalfile.models.Traitement;

import java.util.List;

@Service
public class TraitementService {
    private final TraitementDao traitementDao;

    @Autowired
    public TraitementService(TraitementDao traitementDao) {
        this.traitementDao = traitementDao;
    }

    public Traitement save(Traitement traitement) {
        return this.traitementDao.saveAndFlush (traitement);
    }

    public void deleteById(Long id) {
        this.traitementDao.deleteById (id);
    }

    public Traitement findOne(Long id) {
        return this.traitementDao.getOne (id);
    }

    public List<Traitement> findAll() {
        return this.traitementDao.findAll ();
    }
}
