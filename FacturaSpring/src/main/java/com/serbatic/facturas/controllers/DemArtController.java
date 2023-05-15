package com.serbatic.facturas.controllers;

import com.serbatic.facturas.accessingData.*;
import com.serbatic.facturas.service.DemArtService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demArt")
public class DemArtController {

    @Autowired
    private DemArtService demArtService;

    @PostMapping(path = "/add")
    public @ResponseBody String addnewDemArt(@RequestParam long artId,
                                             @RequestParam long demandId,
                                             @RequestParam int amount) {
        DemArt savedDemArt = demArtService.addNewDemArt(artId, demandId, amount);
        if(savedDemArt==null){
            return "Cannot save DemArt because the amount is not valid";
        }
        return "DemArt saved with "+savedDemArt.getAmount()+" articles with id "+savedDemArt.getArticle().getIdArt()+" in demand with id "+savedDemArt.getDemand().getIdDemand();
    }

    //Marco's VERSION:

    /*@PatchMapping(path = "/{id}")
    public ResponseEntity<DemArt> updateDemArtPartially(@PathVariable(value = "id") DemArtKey id,
                                                    @RequestBody DemArt demArtDetails) throws ResourceNotFoundException {
        DemArt updatedDemArt = demArtService.updateDemArtPartially(id, demArtDetails);
        return ResponseEntity.ok(updatedDemArt);
    }*/


    //Juan's VERSION:
    @PatchMapping(path = "/{idDemArt}")
    public ResponseEntity<DemArt> updateDemArtPartially(@PathVariable(value = "idDemArt") String id,
                                                        @RequestBody DemArt demArtDetails) throws ResourceNotFoundException {
        String[] parts = id.split("-");
        DemArtKey demArtKey = new DemArtKey( Long.valueOf(parts[1]),Long.valueOf((parts[0])));
        DemArt updatedDemArt = demArtService.updateDemArtPartially(demArtKey, demArtDetails);
        return ResponseEntity.ok(updatedDemArt);
    }

    // This returns a json with the user information
    /*@GetMapping(path = "/{id}")
    public ResponseEntity<DemArt> findDemArt(@PathVariable(value = "id") DemArtKey id)
            throws ResourceNotFoundException {
        DemArt demArt = demArtService.findDemArt(id);
        return ResponseEntity.ok().body(demArt);
    }*/


    @GetMapping(path = "/{demandId}/{articleId}")
    public ResponseEntity<DemArt> findDemArt(
            @PathVariable(value = "demandId") String demandId,
            @PathVariable (value="articleId") String articleId)
        throws ResourceNotFoundException{
        DemArtKey id = new DemArtKey(Long.valueOf(demandId),Long.valueOf(articleId));
        DemArt demArt = demArtService.findDemArt(id);
        return ResponseEntity.ok().body(demArt);
    }


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<DemArt> getAllDemArts() {
        // This returns a JSON or XML with the users
        return demArtService.getAllDemArts();
    }

    // DELETE
    @DeleteMapping(path = "/{idDemArt}")
    public @ResponseBody String deleteDemArt(@PathVariable("idDemArt") String id) {
        String[] parts = id.split("-");
        DemArtKey demArtKey = new DemArtKey(Long.valueOf((parts[0])), Long.valueOf(parts[1]));
        demArtService.deleteDemArt(demArtKey);

        return String.format("DemArt %s deleted ", id);
    }
}
