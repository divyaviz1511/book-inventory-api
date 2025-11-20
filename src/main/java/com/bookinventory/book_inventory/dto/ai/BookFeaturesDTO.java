package com.bookinventory.book_inventory.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookFeaturesDTO {
    private String title;
    private String author;
    private String genre;
    private Integer pageCount;
    private String language;
    private Integer releasedYear;
}