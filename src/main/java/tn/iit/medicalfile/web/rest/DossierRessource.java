package tn.iit.medicalfile.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.dto.DossierDto;
import tn.iit.medicalfile.domain.Dossier;
import tn.iit.medicalfile.factory.PatientFactory;
import tn.iit.medicalfile.services.DossierService;
import tn.iit.medicalfile.services.PatientService;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin("*")
@RequestMapping(value = "/api/dossiers")
@RestController()
public class DossierRessource {

    private final Logger logger= LoggerFactory.getLogger (DossierRessource.class);
    private final DossierService dossierService;
    private final PatientService patientService;

    public DossierRessource(DossierService dossierService, PatientService patientService) {
        this.dossierService = dossierService;
        this.patientService = patientService;
    }

    @GetMapping("{id}")
    public DossierDto findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Dossier {}",id);
        return this.dossierService.findOne (id);
    }

    @GetMapping
    public Collection<DossierDto> findAll(){
        this.logger.debug ("Getting all dossiers");
        return this.dossierService.findAll ();
    }

    @PostMapping
    public DossierDto add(@Valid @RequestBody DossierDto dossierDto){
        this.logger.debug ("Adding new Dossier {}",dossierDto);
        Dossier dossier = new Dossier (PatientFactory.patientDtoToPatient (this.patientService.findOne (dossierDto.getPatientId ())));
        return this.dossierService.save (dossierDto);
    }

    @PutMapping
    public DossierDto update(@RequestBody DossierDto dossierDto){
        this.logger.debug ("Updating Dossier {} with {}",dossierDto.getId (),dossierDto);
        return this.dossierService.save (dossierDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id){
        this.logger.debug ("Deleting Dossier {}",id);
        this.dossierService.deleteById (id);
    }
}
