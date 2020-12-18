package tn.iit.medicalfile.factory;

import tn.iit.medicalfile.domain.Dossier;
import tn.iit.medicalfile.dto.DossierDto;
import tn.iit.medicalfile.dto.PatientDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DossierFactory {

    public static DossierDto dossierToDossierDto(Dossier dossier)
    {
        DossierDto dossierDto = new DossierDto ();
        dossierDto.setId (dossier.getId ());
        dossierDto.setPatientId (dossier.getPatient ().getId ());
        return dossierDto;
    }

    public static Dossier dossierDtoToDossier(DossierDto dossierDto)
    {
        Dossier dossier = new Dossier ();
        dossier.setId (dossierDto.getId ());
        dossier.setPatient (PatientFactory.patientDtoToPatient (new PatientDto (dossierDto.getPatientId ())));
        return dossier;
    }

    public static Collection<DossierDto> dossiersToDossierDtos(Collection<Dossier> categories)
    {
        List<DossierDto> dossierDtoList = new ArrayList<> ();
        categories.forEach(dossier -> {
            dossierDtoList.add (dossierToDossierDto (dossier));
        });
        return dossierDtoList;
    }
}
