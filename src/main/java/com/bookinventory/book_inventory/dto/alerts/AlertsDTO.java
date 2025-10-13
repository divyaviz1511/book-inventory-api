package com.bookinventory.book_inventory.dto.alerts;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertsDTO {
    int bookId;
    String bookTitle;
    String message;
    LocalDateTime createdAt;
    
}
