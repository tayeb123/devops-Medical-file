package tn.iit.medicalfile.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.dto.DossierDto;
import tn.iit.medicalfile.models.Dossier;
import tn.iit.medicalfile.services.DossierService;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/dossiers")
@RestController()
public class DossierController {

    private final Logger logger= LoggerFactory.getLogger (DossierController.class);
    private final DossierService dossierService;

    @Autowired
    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @GetMapping("{id}")
    public Dossier findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Dossier {}",id);
        return this.dossierService.findOne (id);
    }

    @GetMapping("/all")
    public List<Dossier> findAll(){
        this.logger.debug ("Getting all dossiers");
        return this.dossierService.findAll ();
    }

    @PostMapping("/add")
    public Dossier add(@RequestBody DossierDto dossierDto){
        this.logger.debug ("Adding new Dossier {}",dossierDto);
        Dossier dossier = new Dossier (dossierDto.getPatient ());
        return this.dossierService.save (dossier);
    }

    @PutMapping("{id}")
    public Dossier update(@PathVariable("id") Long id,@RequestBody DossierDto dossierDto){
        this.logger.debug ("Updating Dossier {} with {}",id,dossierDto);
        Dossier dossier=this.dossierService.findOne (id);
        dossier.setPatient (dossierDto.getPatient ());
        return this.dossierService.save (dossier);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id){
        this.logger.debug ("Deleting Dossier {}",id);
        this.dossierService.deleteById (id);
    }
}
