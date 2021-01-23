package com.example.service;

import com.example.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository <Invoice, String>{

}
