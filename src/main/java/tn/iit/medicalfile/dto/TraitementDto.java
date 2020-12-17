package tn.iit.medicalfile.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tn.iit.medicalfile.models.Dossier;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TraitementDto {
    private Dossier dossier;
    private long medicamentId;
}
