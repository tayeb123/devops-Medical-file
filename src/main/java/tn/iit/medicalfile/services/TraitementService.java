package tn.iit.medicalfile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.medicalfile.dao.TraitementDao;
import tn.iit.medicalfile.dto.MedicamentDto;
import tn.iit.medicalfile.dto.TraitementDto;
import tn.iit.medicalfile.factory.TraitementFactory;

import javax.transaction.Transactional;
import java.util.Collection;

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
        traitementDto.setMedicamentDosage (medicamentDto.getDosage ());
        traitementDto.setMedicamentName (medicamentDto.getName ());
        traitementDto.setMedicamentPrice (medicamentDto.getPrice ());
        return traitementDto;
    }

    public Collection<TraitementDto> findAll() {
        Collection<TraitementDto> traitementDtos = TraitementFactory.traitementsToTraitementDtos (this.traitementDao.findAll ());
        traitementDtos.stream ().map (traitementDto -> {
            MedicamentDto medicamentDto = this.storeManagementClientService.getMedicamentById (traitementDto.getMedicamentId ());
            traitementDto.setMedicamentDosage (medicamentDto.getDosage ());
            traitementDto.setMedicamentName (medicamentDto.getName ());
            traitementDto.setMedicamentPrice (medicamentDto.getPrice ());
            return traitementDto;
        });
        return traitementDtos;
    }
}
