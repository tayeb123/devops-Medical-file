package tn.iit.medicalfile.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.dto.PatientDto;
import tn.iit.medicalfile.domain.Patient;
import tn.iit.medicalfile.services.PatientService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/api/patients")
@RestController()
public class PatientRessource {

    private final Logger logger= LoggerFactory.getLogger (PatientRessource.class);
    private final PatientService patientService;

    public PatientRessource(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("{id}")
    public PatientDto findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Patient {}",id);
        return this.patientService.findOne (id);
    }

    @GetMapping
    public Collection<PatientDto> findAll(){
        this.logger.debug ("Getting all patients");
        return this.patientService.findAll ();
    }

    @PostMapping
    public PatientDto add(@Valid @RequestBody PatientDto patientDto){
        this.logger.debug ("Adding new Patient {}",patientDto);
        return this.patientService.save (patientDto);
    }

    @PutMapping
    public PatientDto update(@Valid @RequestBody PatientDto patientDto){
        this.logger.debug ("Updating Patient {} with {}",patientDto.getId (),patientDto);
        return this.patientService.save (patientDto);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id){
        this.logger.debug ("Deleting Patient {}",id);
        this.patientService.deleteById (id);
    }
}
