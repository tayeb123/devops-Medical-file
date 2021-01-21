package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.TraitementDao;
import tn.iit.medicalfile.dto.MedicamentDto;
import tn.iit.medicalfile.dto.TraitementDto;
import tn.iit.medicalfile.factory.TraitementFactory;
import tn.iit.medicalfile.web.rest.errors.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TraitementService {
    private final TraitementDao traitementDao;
    private final StoreManagementClientService storeManagementClientService;

    @Autowired
    public TraitementService(TraitementDao traitementDao, StoreManagementClientService storeManagementClientService) {
        this.traitementDao = traitementDao;
        this.storeManagementClientService = storeManagementClientService;
    }

    public TraitementDto save(TraitementDto traitementDto) {
        this.traitementDao.saveAndFlush (TraitementFactory.traitementDtoToTraitement (traitementDto));
        return traitementDto;
    }

    public void deleteById(Long id) {
        this.traitementDao.deleteById (id);
    }

    public TraitementDto findOne(Long id) {
        TraitementDto traitementDto= TraitementFactory.traitementToTraitementDto (this.traitementDao.getOne (id));
        MedicamentDto medicamentDto = this.storeManagementClientService.getMedicamentById (traitementDto.getMedicamentId ());
        traitementDto.setMedicamentName (medicamentDto.getNom ());
        traitementDto.setMedicamentPrice (medicamentDto.getPrix ());
        return traitementDto;
    }

    public Collection<TraitementDto> findAll() {
        Collection<TraitementDto> traitementDtos = TraitementFactory.traitementsToTraitementDtos (this.traitementDao.findAll ());
        traitementDtos.forEach (traitementDto -> {
            MedicamentDto medicamentDto = this.storeManagementClientService.getMedicamentById (traitementDto.getMedicamentId ());
            traitementDto.setMedicamentName (medicamentDto.getNom ());
            traitementDto.setMedicamentPrice (medicamentDto.getPrix ());
        });
        return traitementDtos;
    }
    public Collection<TraitementDto> findAllByDossierId(Long dossierId){
        Collection<TraitementDto> traitementDtos = TraitementFactory.traitementsToTraitementDtos (this.traitementDao.findAllByDossier_Id (dossierId));
        List<Long> ids = traitementDtos.stream ().map (TraitementDto::getMedicamentId).collect(Collectors.toList());
        List<MedicamentDto> medicaments=this.storeManagementClientService.getMedicamentsByIds (ids);
        traitementDtos.forEach (traitementDto -> {
            MedicamentDto medicamentDto = medicaments.stream ()
                    .filter (medicamentDto1 -> medicamentDto1.getId () == traitementDto.getMedicamentId ())
                    .findFirst ()
                    .orElseThrow (() -> new RuntimeException());
            traitementDto.setMedicamentName (medicamentDto.getNom ());
            traitementDto.setMedicamentPrice (medicamentDto.getPrix ());
        });
        return traitementDtos;
    }

}
