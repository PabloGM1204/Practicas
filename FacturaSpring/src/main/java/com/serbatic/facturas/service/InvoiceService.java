package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvoiceService implements InvoiceServiceInterface{

    @Autowired
    private DemArtService demArtService;


    @Autowired
    private DemandService demandService;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice addNewInvoice(Date date, Demand demand) {
        Invoice inv = new Invoice();
        inv.setDate(date);
        demand.setInvoiced(true);
        inv.setDemand(demand);
        demandService.updateDemandPartially(demand.getIdDemand(), demand);
        return invoiceRepository.save(inv);
    }

    @Override
    public Invoice updateInvoicePartially(Long invId, Invoice invDetails) throws ResourceNotFoundException {
        Invoice inv=invoiceRepository.findById(invId).orElseThrow(() -> new ResourceNotFoundException("Invoice not found on :: "+invId));

        if(invDetails.getDate()!=null){
            inv.setDate(invDetails.getDate());
        }
        if(invDetails.getDemand()!=null){
            inv.setDemand(invDetails.getDemand());
        }
        return invoiceRepository.save(inv);
    }

    @Override
    public Invoice findInvoice(Long invId) throws ResourceNotFoundException {
        return invoiceRepository.findById(invId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found on : " + invId));
    }

    @Override
    public void deleteInvoice(Long id) {
         invoiceRepository.deleteById(id);
    }

    @Override
    public Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
