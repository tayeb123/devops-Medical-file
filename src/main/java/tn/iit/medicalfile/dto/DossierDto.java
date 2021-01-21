package tn.iit.medicalfile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DossierDto{
    private long id;
    @NotNull
    private long patientId;
    private String patientNom;
    public DossierDto(Long id){
        this.id=id;
    }
    private Collection<TraitementDto> traitement;
}
