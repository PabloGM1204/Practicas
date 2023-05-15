package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.Invoice;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.Date;

public interface InvoiceServiceInterface {
    Invoice addNewInvoice(Date date, Demand demand);

    Invoice updateInvoicePartially(Long invId, Invoice invDetails) throws ResourceNotFoundException;

    Invoice findInvoice(Long invId) throws ResourceNotFoundException;

    void deleteInvoice(Long id);

    Iterable<Invoice> getAllInvoices();
}
