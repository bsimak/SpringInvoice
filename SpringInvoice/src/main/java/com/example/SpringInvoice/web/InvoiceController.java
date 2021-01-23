package com.example.SpringInvoice.web;

import com.example.SpringInvoice.dto.InvoiceDto;
import com.example.SpringInvoice.model.Invoice;
import com.example.SpringInvoice.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    // ------------- GET Method ---------------
    // @RequestMapping(value="/invoices", method = RequestMethod.GET)
    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    // ---------------POST Method ----------------
    @PostMapping("/invoices")
    public Invoice create( @Valid @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
