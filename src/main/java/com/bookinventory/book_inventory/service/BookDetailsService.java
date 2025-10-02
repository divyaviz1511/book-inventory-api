package com.bookinventory.book_inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventory.book_inventory.model.BookDetails;
import com.bookinventory.book_inventory.repository.BookDetailsRepository;
import com.bookinventory.book_inventory.dto.*;
import com.bookinventory.book_inventory.exceptions.ResourceNotFoundException;

@Service
public class BookDetailsService {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    public List<BookDetailsResponse> getAllBookDetails() {
        List<BookDetailsResponse> bookDetailsResponses = new ArrayList<>();
        List<BookDetails> bookDetails = bookDetailsRepository.findAll();
        for(BookDetails bookDetail : bookDetails) {
            bookDetailsResponses.add(this.getBookDetailResponseFromBookDetailEntity(bookDetail));
        }

        return bookDetailsResponses;
    }

    public BookDetailsResponse getBookDetailsById(int id) {
        BookDetails bookDetails = bookDetailsRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Book with id: " + id + " not found!"));
        return this.getBookDetailResponseFromBookDetailEntity(bookDetails);
    }

    public BookDetailsResponse addBookDetails(BookDetailsRequest bookDetailsRequest) {
        BookDetails bookDetails = this.getBookDetailsEntityFromBookDetailsRequestDTO(bookDetailsRequest);
        return getBookDetailResponseFromBookDetailEntity(bookDetailsRepository.save(bookDetails));
    }

    public BookDetailsResponse updateBookDetailsById(int id, BookDetailsRequest bookDetailsRequest) {
        BookDetails bookDetails = bookDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + id + " not found"));
        
        if (bookDetailsRequest.getAuthor() != null) bookDetails.setAuthor(bookDetailsRequest.getAuthor());
        if (bookDetailsRequest.getTitle() != null) bookDetails.setTitle(bookDetailsRequest.getTitle());
        if (bookDetailsRequest.getPrice() != null) bookDetails.setPrice(bookDetailsRequest.getPrice());
        if (bookDetailsRequest.getQuantity() != null) bookDetails.setQuantity(bookDetailsRequest.getQuantity());
        if (bookDetailsRequest.getLanguage() != null) bookDetails.setLanguage(bookDetailsRequest.getLanguage());
        if (bookDetailsRequest.getReleasedDate() != null) bookDetails.setReleased_date(bookDetailsRequest.getReleasedDate());

        bookDetailsRepository.save(bookDetails);
        return this.getBookDetailResponseFromBookDetailEntity(bookDetails);
    }

    public void deleteBookDetailById(int id) {
        BookDetails bookDetails = bookDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + id + " not found"));
        bookDetailsRepository.delete(bookDetails);
    }


    //Conversion functions btw DTO and Entity. 
    private BookDetails getBookDetailsEntityFromBookDetailsRequestDTO(BookDetailsRequest bookDetailsRequest) {
        return new BookDetails(bookDetailsRequest.getTitle(), bookDetailsRequest.getAuthor(), bookDetailsRequest.getPrice(), bookDetailsRequest.getQuantity(), bookDetailsRequest.getLanguage(), bookDetailsRequest.getReleasedDate());

    }

    private BookDetailsResponse getBookDetailResponseFromBookDetailEntity(BookDetails bookDetails) {
        return new BookDetailsResponse(bookDetails.getBook_id(), bookDetails.getTitle(), bookDetails.getAuthor(), 
            bookDetails.getPrice(), bookDetails.getQuantity(), bookDetails.getLanguage(), bookDetails.getReleased_date());    
    }
}
