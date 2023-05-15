package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.DemandRepository;
import com.serbatic.facturas.accessingData.User;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemandService implements  DemandServiceInterface{
    @Autowired
    private DemandRepository demandRepository;
    @Override
    public Demand addNewDemand(Date date, User user) {
        Demand dem=new Demand();
        dem.setDate(date);
        dem.setUser(user);
        return demandRepository.save(dem);
    }

    @Override
    public Demand updateDemandPartially(Long demId, Demand demDetails) throws ResourceNotFoundException {
        Demand dem=demandRepository.findById(demId).orElseThrow(() -> new ResourceNotFoundException("Demand not found on :: "+demId));

        if(demDetails.getDate()!=null){
            dem.setDate(demDetails.getDate());
        }
        if(demDetails.getUser()!=null){
            dem.setUser(demDetails.getUser());
        }

        return demandRepository.save(dem);    }

    @Override
    public Demand findDemand(Long demId) throws ResourceNotFoundException {
        return demandRepository.findById(demId)
                .orElseThrow(() -> new ResourceNotFoundException("Demand not found on : " + demId));

    }

    @Override
    public void deleteDemand(Long id) {
        demandRepository.deleteById(id);
    }

    @Override
    public Iterable<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    public Iterable<Demand> getNoInvoiced() {
        List<Demand> demands = new ArrayList<>();
        for (Demand iterabledemand : demandRepository.findAll()) {
            if (!iterabledemand.isInvoiced()) {
                demands.add(iterabledemand);
            }
        }
        return demands;
    }
}
