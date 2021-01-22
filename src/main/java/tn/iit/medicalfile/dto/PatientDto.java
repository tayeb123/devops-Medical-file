package tn.iit.medicalfile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @Size(min = 8,max = 8)
    private String cin;

    public PatientDto(long id)
    {
        this.id=id;
    }

}
