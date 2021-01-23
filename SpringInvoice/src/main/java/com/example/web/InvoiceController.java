package com.example.web;

import com.example.dto.InvoiceDto;
import com.example.model.Invoice;
import com.example.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    // ------------- GET Method ---------------
    // @RequestMapping(value="/invoices", method = RequestMethod.GET)
    @GetMapping("/invoices")
    public Iterable<Invoice> invoices() {
        return invoiceService.findAll();
    }

    // Eingabe e.g.: http://localhost:8080/invoices/user/Barbara
    @GetMapping("/invoices/user/{userId}")
    public Iterable<Invoice> invoicesByUser(@PathVariable("userId")String user_id) {
        return invoiceService.findByUserId(user_id);
    }

    // Eingabe e.g.: http://localhost:8080/invoices/amount/50
    @GetMapping("/invoices/amount/{amount}")
    public Iterable<Invoice> invoicesAmount(@PathVariable Integer amount) {
        return invoiceService.findByAmount(amount);
    }

    // ---------------POST Method ----------------
    @PostMapping("/invoices")
    public Invoice create(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
