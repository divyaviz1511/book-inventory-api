package com.bookinventory.book_inventory.model;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetails {
    public static final int  THRESHOLD = 5; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    
    private String title;

    private String author;

    private BigDecimal price;

    @Column(name = "qty")
    private int quantity;

    private String language;

    private LocalDate releasedDate;

    public BookDetails(String title, String author, BigDecimal price, int quantity, String language, LocalDate releasedDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.language = language;
        this.releasedDate = releasedDate;
    }
}
