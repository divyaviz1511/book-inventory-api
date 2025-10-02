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
    private LocalDate releasedDate;
}
