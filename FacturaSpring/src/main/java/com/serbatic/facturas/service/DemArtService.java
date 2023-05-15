package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemArtService implements DemArtServiceInterface{
    @Autowired
    private DemArtRepository demArtRepository;

    @Autowired
    private DemandService demandService;
    @Autowired
    private ArticleService articleService;

    @Override
    public DemArt addNewDemArt(Long art, Long demId, int amount) {
        if (amount <=0){
            System.out.println("Error, amount cannot be 0");
            return null;
        }
        Article article=articleService.findArticle(art);
        DemArt demArt=new DemArt(article,demandService.findDemand(demId));
        //consultar la cantidad disponible, si no hay suficiente error
        if(article.getStock()>=amount){
            demArt.setAmount(amount);
            article.setStock(article.getStock()-amount);
        }else{
            System.out.println( "Cannot add because there are only "+article.getStock()+" of "+ article.getName()+" in stock");
            return null;
        }
        DemArtKey demArtKey=new DemArtKey(demArt.getDemand().getIdDemand(),demArt.getArticle().getIdArt());
        demArt.setId(demArtKey);
        return demArtRepository.save(demArt);
    }

    @Override
    public DemArt updateDemArtPartially(DemArtKey demArtKey, DemArt demArtDetails) throws ResourceNotFoundException {
        DemArt demArt=demArtRepository.findById(demArtKey).orElseThrow(() -> new ResourceNotFoundException("Demand not found on :: "+demArtKey));
        if(demArtDetails.getArticle()!=null){
            demArt.setArticle(demArtDetails.getArticle());
        }

        if(demArtDetails.getDemand()!=null){
            demArt.setDemand(demArtDetails.getDemand());
        }

        if(demArtDetails.getAmount()>0){
            demArt.setAmount(demArtDetails.getAmount());
        }
        return demArtRepository.save(demArt);
    }

    @Override
    public DemArt findDemArt(DemArtKey demArtId) throws ResourceNotFoundException {
        return demArtRepository.findById(demArtId)
                .orElseThrow(() -> new ResourceNotFoundException("DemArt not found on : " + demArtId));
    }

    @Override
    public void deleteDemArt(DemArtKey id) {
        demArtRepository.deleteById(id);
    }

    @Override
    public Iterable<DemArt> getAllDemArts() {
        return demArtRepository.findAll();
    }

}
