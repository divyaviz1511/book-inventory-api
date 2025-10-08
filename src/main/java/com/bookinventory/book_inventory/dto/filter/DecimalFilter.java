package com.bookinventory.book_inventory.dto.filter;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecimalFilter {
    private BigDecimal value;
    private int operation;
}
