package tn.iit.medicalfile.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.dto.PatientDto;
import tn.iit.medicalfile.models.Patient;
import tn.iit.medicalfile.services.PatientService;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/patients")
@RestController()
public class PatientController {

    private final Logger logger= LoggerFactory.getLogger (PatientController.class);
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("{id}")
    public Patient findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Patient {}",id);
        return this.patientService.findOne (id);
    }

    @GetMapping("/all")
    public List<Patient> findAll(){
        this.logger.debug ("Getting all patients");
        return this.patientService.findAll ();
    }

    @PostMapping("/add")
    public Patient add(@RequestBody PatientDto patientDto){
        this.logger.debug ("Adding new Patient {}",patientDto);
        Patient patient = new Patient (patientDto.getName (),patientDto.getCin (),patientDto.getBirthday ());
        return this.patientService.save (patient);
    }

    @PutMapping("{id}")
    public Patient update(@PathVariable("id") Long id,@RequestBody PatientDto patientDto){
        this.logger.debug ("Updating Patient {} with {}",id,patientDto);
        Patient patient=this.patientService.findOne (id);
        patient.setName (patientDto.getName ());
        patient.setCin (patientDto.getCin ());
        patient.setBirthday (patientDto.getBirthday ());
        return this.patientService.save (patient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id){
        this.logger.debug ("Deleting Patient {}",id);
        this.patientService.deleteById (id);
    }
}
