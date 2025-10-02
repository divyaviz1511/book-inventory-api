package com.bookinventory.book_inventory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bookinventory.book_inventory.validations.ValidBookTitle;
import com.bookinventory.book_inventory.validations.ValidLanguage;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDetailsRequest {
    @NotBlank(message="Title cannot be blank")
    @ValidBookTitle
    private String title;

    @NotBlank(message="Author cannot be blank")
    private String author;

    @DecimalMin(value="0.0", inclusive=false, message="Price must be greater than 0")
    private BigDecimal price;

    @Min(value=0, message="Quantity can be 0 or more")
    private Integer quantity;

    @ValidLanguage
    private String language;


    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate releasedDate;
}