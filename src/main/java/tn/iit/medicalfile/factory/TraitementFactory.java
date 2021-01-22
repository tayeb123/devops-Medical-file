package tn.iit.medicalfile.factory;

import tn.iit.medicalfile.domain.Traitement;
import tn.iit.medicalfile.dto.DossierDto;
import tn.iit.medicalfile.dto.MedicamentDto;
import tn.iit.medicalfile.dto.TraitementDto;
import tn.iit.medicalfile.services.StoreManagementClientService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TraitementFactory {


    public static TraitementDto traitementToTraitementDto(Traitement traitement)
    {
        TraitementDto traitementDto = new TraitementDto ();
        traitementDto.setId (traitement.getId ());
        traitementDto.setDossierId (traitement.getDossier ().getId ());
        traitementDto.setMedicamentId (traitement.getMedicamentId ());
        return traitementDto;
    }

    public static Traitement traitementDtoToTraitement(TraitementDto traitementDto)
    {
        Traitement traitement = new Traitement ();
        traitement.setId (traitementDto.getId ());
        traitement.setDossier (DossierFactory.dossierDtoToDossier (new DossierDto (traitementDto.getDossierId ())));
        traitement.setMedicamentId (traitementDto.getMedicamentId ());
        return traitement;
    }

    public static Collection<TraitementDto> traitementsToTraitementDtos(Collection<Traitement> traitements)
    {
        List<TraitementDto> traitementDtoList = new ArrayList<> ();
        traitements.forEach(traitement -> {
            traitementDtoList.add (traitementToTraitementDto (traitement));
        });
        return traitementDtoList;
    }
}
