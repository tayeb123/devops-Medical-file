package tn.iit.medicalfile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DossierDto{
    private long id;
    @NotNull
    private long patientId;
    public DossierDto(Long id){
        this.id=id;
    }
}
