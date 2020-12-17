package tn.iit.medicalfile.controllers;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.iit.medicalfile.dto.TraitementDto;
import tn.iit.medicalfile.models.Traitement;
import tn.iit.medicalfile.services.TraitementService;
import tn.iit.medicalfile.utils.Links;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RequestMapping(value = "/traitements")
@RestController()
public class TraitementController {

    private final Logger logger = LoggerFactory.getLogger (TraitementController.class);
    private final TraitementService traitementService;
    private final RestTemplate restTemplate;

    @Autowired
    public TraitementController(TraitementService traitementService, RestTemplate restTemplate) {
        this.traitementService = traitementService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Traitement {}", id);
        Traitement traitement = this.traitementService.findOne (id);
        JSONObject result = new JSONObject ();
        result.put ("dossier", traitement.getDossier ());
        String url = Links.MEDICAMENTS + "/" + traitement.getMedicamentId ();
        ResponseEntity<JSONObject> medicamentResult = restTemplate.exchange (url, HttpMethod.GET, null, JSONObject.class);
        JSONObject medicament = medicamentResult.getBody ();
        result.put ("medicament", medicament);
        return ResponseEntity.ok (result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        this.logger.debug ("Getting all traitements");
        String url = Links.MEDICAMENTS + "/all";
        List<Traitement> traitementList = this.traitementService.findAll ();
        ResponseEntity<List<JSONObject>> medicamentsResponse = restTemplate.exchange
                (url, HttpMethod.GET, null, new ParameterizedTypeReference<List<JSONObject>> () {
                });
        List<JSONObject> medicaments = medicamentsResponse.getBody ();
        List<JSONObject> results = new ArrayList<> ();
        if (medicaments != null) {
            for (Traitement traitement:traitementList)
            {
                JSONObject jsonObject = new JSONObject ();
                jsonObject.put ("dossier", traitement.getDossier ());
                for (JSONObject medicament : medicaments) {
                    long medicamentId= Long.parseLong (medicament.get ("id").toString ());
                    if (medicamentId== traitement.getMedicamentId ())
                    {
                        jsonObject.put ("medicament", medicament);
                        break;
                    }
                }
                results.add (jsonObject);
            }
            return ResponseEntity.ok (results);
        }
        return null;
    }

    @PostMapping("/add")
    public Traitement add(@RequestBody TraitementDto traitementDto) {
        this.logger.debug ("Adding new Traitement {}", traitementDto);
        Traitement patient = new Traitement (traitementDto.getDossier (), traitementDto.getMedicamentId ());
        return this.traitementService.save (patient);
    }

    @PutMapping("{id}")
    public Traitement update(@PathVariable("id") Long id, @RequestBody TraitementDto traitementDto) {
        this.logger.debug ("Updating Traitement {} with {}", id, traitementDto);
        Traitement patient = this.traitementService.findOne (id);
        patient.setDossier (traitementDto.getDossier ());
        patient.setMedicamentId (traitementDto.getMedicamentId ());
        return this.traitementService.save (patient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        this.logger.debug ("Deleting Traitement {}", id);
        this.traitementService.deleteById (id);
    }
}
