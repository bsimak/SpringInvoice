package com.example.SpringInvoice.service;

import com.example.SpringInvoice.model.Invoice;
import com.example.SpringInvoice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    private final UserService userService;
    private final String cdnUrl;

    public InvoiceService(UserService userService,
                          @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }
    @PostConstruct
    public void init() {
        System.out.println("Fetch PDF Template from S3...");
        // TODO download from s3 and save locally
    }
    @PreDestroy
    // works only when application is really shutdown
    public void shutdown(){
        System.out.println("Deleting downloaded template");
    }

    // -------- Start of the methods --------

    public List<Invoice> findAll() {
            return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }
        Invoice invoice = new Invoice(userId, amount,
                cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }

}