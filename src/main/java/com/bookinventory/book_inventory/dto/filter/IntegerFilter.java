package com.bookinventory.book_inventory.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerFilter {
    private Integer value;
    private int operation;
}
