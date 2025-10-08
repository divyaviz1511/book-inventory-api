package com.bookinventory.book_inventory.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    String title;
    String author;
    String language;

    DecimalFilter price;
    IntegerFilter quantity;
    IntegerFilter releasedYear; 
}
