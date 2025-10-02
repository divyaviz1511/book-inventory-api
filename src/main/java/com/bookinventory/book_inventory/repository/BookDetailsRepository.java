package com.bookinventory.book_inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookinventory.book_inventory.model.BookDetails;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {
    
}
