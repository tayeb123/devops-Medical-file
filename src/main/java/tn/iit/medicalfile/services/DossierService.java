package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.DossierDao;
import tn.iit.medicalfile.dto.DossierDto;
import tn.iit.medicalfile.factory.DossierFactory;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@Service
public class DossierService {
    private final DossierDao dossierDao;
    private final TraitementService traitementService;
    @Autowired
    public DossierService(DossierDao dossierDao, TraitementService traitementService) {
        this.dossierDao = dossierDao;
        this.traitementService = traitementService;
    }

    public DossierDto save(DossierDto dossierDto) {
       this.dossierDao.saveAndFlush (DossierFactory.dossierDtoToDossier (dossierDto));
       return dossierDto;
    }

    public void deleteById(Long id) {
        this.dossierDao.deleteById (id);
    }
    public DossierDto findOne(Long id) {
        DossierDto dossierDto = DossierFactory.dossierToDossierDto(this.dossierDao.getOne(id));
        dossierDto.setTraitement (this.traitementService.findAllByDossierId (id));
        return dossierDto;
    }

    public Collection<DossierDto> findAll() {
        return DossierFactory.dossiersToDossierDtos (this.dossierDao.findAll ());
    }
}
