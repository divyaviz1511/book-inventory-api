package com.bookinventory.book_inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bookinventory.book_inventory.model.BookDetails;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer>, JpaSpecificationExecutor<BookDetails> {
    
}
