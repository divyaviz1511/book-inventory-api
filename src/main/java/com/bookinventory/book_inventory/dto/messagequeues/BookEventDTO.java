package com.bookinventory.book_inventory.dto.messagequeues;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEventDTO {
    private String eventName;
    private int bookId; 
    private LocalDate generatedAt;
}
