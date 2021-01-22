package tn.iit.medicalfile.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TraitementDto {

    @NotNull
    private long id;
    @NotNull
    private long dossierId;
    @NotNull
    @Size(min = 3)
    @NotEmpty
    private String medicamentName;
    @NotNull
    private float medicamentPrice;
    @NotNull
    private long medicamentId;
}
