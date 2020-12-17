package tn.iit.medicalfile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tn.iit.medicalfile.models.Patient;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DossierDto {
    private Patient patient;
}
