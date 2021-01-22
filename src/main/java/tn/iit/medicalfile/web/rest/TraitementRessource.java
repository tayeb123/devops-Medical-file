package tn.iit.medicalfile.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.dto.TraitementDto;
import tn.iit.medicalfile.services.TraitementService;
import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin("*")
@RequestMapping(value = "/api/traitements")
@RestController()
public class TraitementRessource {

    private final Logger logger = LoggerFactory.getLogger (TraitementRessource.class);
    private final TraitementService traitementService;

    public TraitementRessource(TraitementService traitementService) {
        this.traitementService = traitementService;
    }

    @GetMapping("{id}")
    public TraitementDto findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Traitement {}", id);
        return this.traitementService.findOne (id);

    }

    @GetMapping
    public Collection<TraitementDto> findAll() {
        this.logger.debug ("Getting all traitements");
        return this.traitementService.findAll ();
    }

    @PostMapping
    public TraitementDto add(@Valid @RequestBody TraitementDto traitementDto) {
        this.logger.debug ("Adding new Traitement {}", traitementDto);
        return this.traitementService.save (traitementDto);
    }

    @PutMapping
    public TraitementDto update(@Valid @RequestBody TraitementDto traitementDto) {
        this.logger.debug ("Updating Traitement {} with {}", traitementDto.getId (), traitementDto);
        return this.traitementService.save (traitementDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        this.logger.debug ("Deleting Traitement {}", id);
        this.traitementService.deleteById (id);
    }
}
