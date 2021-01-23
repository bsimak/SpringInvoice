package com.example.service;

import com.example.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jdbc.repository.query.Query;

public interface InvoiceRepository extends CrudRepository <Invoice, String>{

    @Query("SELECT id, pdf_url, user_id, amount FROM \"invoices\" where user_id = :userId")
    Iterable<Invoice> findByUserId(@Param("userId") String userId);

    @Query("SELECT id, pdf_url, user_id, amount FROM \"invoices\" where amount = :amount")
    Iterable<Invoice> findByAmount(@Param("amount") Integer amount);

}
