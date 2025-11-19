package com.bookinventory.book_inventory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsResponse {
    private int id;
    private String title;
    private String author;
    private BigDecimal price;
    private int quantity;
    private String language;
    private String genre;
    private int pageCount;
    private LocalDate releasedDate;
    private Double bestSellerProbability;

    public BookDetailsResponse(int id, String title, String author, BigDecimal price, int quantity, String language, String genre, int pageCount, LocalDate releasedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.language = language;
        this.genre = genre;
        this.pageCount = pageCount;
        this.releasedDate = releasedDate;
    }
}
