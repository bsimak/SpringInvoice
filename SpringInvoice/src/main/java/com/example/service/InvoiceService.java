package com.example.service;

import com.example.model.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    public final InvoiceRepository invoiceRepository;

   // private final UserService userService;
    private final String cdnUrl;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          @Value("${cdn.url}") String cdnUrl) {
        this.invoiceRepository = invoiceRepository;
        this.cdnUrl = cdnUrl;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetch PDF Template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    // works only when application is really shutdown
    public void shutdown() {
        System.out.println("Deleting downloaded template");
    }

    @Transactional
    public Iterable<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
    // ------ FIND Invoices by User -----------
    @Transactional
    public Iterable<Invoice> findByUserId(String UserId) {
        System.out.println("Suche Invoices nach User");
        return invoiceRepository.findByUserId(UserId);
    }
    // ------ FIND Invoices by Amount -----------
    @Transactional
    public Iterable<Invoice> findByAmount(Integer amount) {
        System.out.println("Suche Invoices nach Amount");
        return invoiceRepository.findByAmount(amount);
    }
    // ------- CREATE Invoices -----------------
    @Transactional
    public Invoice create(String userId, Integer amount) {
        String generatePdfUrl = cdnUrl + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setUserId(userId);
        invoice.setAmount(amount);
        invoice.setPdfUrl(generatePdfUrl);
        return invoiceRepository.save(invoice);
    }
}
