package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.*;
import org.apache.velocity.exception.ResourceNotFoundException;

public interface DemArtServiceInterface {

    DemArt addNewDemArt(Long art, Long dem, int amount);

    DemArt updateDemArtPartially(DemArtKey demArtKey, DemArt demArtDetails) throws ResourceNotFoundException;

    DemArt findDemArt(DemArtKey demArtId) throws ResourceNotFoundException;

    void deleteDemArt(DemArtKey id);

    Iterable<DemArt> getAllDemArts();
}
