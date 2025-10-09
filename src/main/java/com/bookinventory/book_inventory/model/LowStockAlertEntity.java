package com.bookinventory.book_inventory.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="low_stock_alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LowStockAlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int bookId;

    @Column(name="book_title")
    private String bookTitle;
    
    private String message;

    @Column(name="created_at")
    private LocalDateTime createAt;

    public LowStockAlertEntity(int bookId, String bookTitle, String message) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.message = message;
        this.createAt = LocalDateTime.now();
    }
}
