package com.bookinventory.book_inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bookinventory.book_inventory.model.BookDetails;
import com.bookinventory.book_inventory.model.LowStockAlertEntity;
import com.bookinventory.book_inventory.repository.BookDetailsRepository;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

import com.bookinventory.book_inventory.dto.*;
import com.bookinventory.book_inventory.dto.filter.SearchRequest;
import com.bookinventory.book_inventory.exceptions.ResourceNotFoundException;
import com.bookinventory.book_inventory.messaging.alerts.AlertMessageSender;

@Service
public class BookDetailsService {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    @Autowired
    private AlertMessageSender messageSender;

    @Autowired
    private AIService aiService;

    public List<BookDetailsResponse> getAllBookDetails() {
        List<BookDetailsResponse> bookDetailsResponses = new ArrayList<>();
        List<BookDetails> bookDetails = bookDetailsRepository.findAll();
        for(BookDetails bookDetail : bookDetails) {
            bookDetailsResponses.add(this.getBookDetailResponseFromBookDetailEntity(bookDetail));
        }

        //get probability for the list of books
        /*List<Double> prob = aiService.getBestSellerProbabilities(bookDetails);

        for(int i=0; i<bookDetailsResponses.size(); i++) {
            bookDetailsResponses.get(i).setBestSellerProbability(prob.get(i));
        }*/

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
        if (bookDetailsRequest.getQuantity() != null) {
            bookDetails.setQuantity(bookDetailsRequest.getQuantity());
            if (bookDetailsRequest.getQuantity() < BookDetails.THRESHOLD) {
                String message = "Low Stock Alert ! Book with id: " + bookDetails.getBook_id() + " and title: " + bookDetails.getTitle() + " is low in stock (only " + bookDetails.getQuantity() + " left)";
                LowStockAlertEntity alert = new LowStockAlertEntity(bookDetails.getBook_id() , bookDetails.getTitle(), message);
                messageSender.send(alert);
            }
        }
        if (bookDetailsRequest.getLanguage() != null) bookDetails.setLanguage(bookDetailsRequest.getLanguage());
        if (bookDetailsRequest.getGenre() != null) bookDetails.setGenre(bookDetailsRequest.getGenre());
        if (bookDetailsRequest.getPageCount() != null) bookDetails.setPageCount(bookDetails.getPageCount());
        if (bookDetailsRequest.getReleasedDate() != null) bookDetails.setReleasedDate(bookDetailsRequest.getReleasedDate());

        bookDetailsRepository.save(bookDetails);
        return this.getBookDetailResponseFromBookDetailEntity(bookDetails);
    }

    public void deleteBookDetailById(int id) {
        BookDetails bookDetails = bookDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + id + " not found"));
        bookDetailsRepository.delete(bookDetails);
    }

    private Specification<BookDetails> filterBy(SearchRequest searchRequest) {
        return (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (searchRequest.getTitle() != null && !searchRequest.getTitle().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" +  searchRequest.getTitle().toLowerCase() + "%"));
            }

            if (searchRequest.getAuthor() !=null && !searchRequest.getAuthor().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("author")), "%" + searchRequest.getAuthor().toLowerCase() + "%"));
            }

            if (searchRequest.getLanguage() !=null && !searchRequest.getLanguage().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("language")), "%" + searchRequest.getLanguage().toLowerCase() + "%"));
            }

           if (searchRequest.getPrice() != null && searchRequest.getPrice().getValue() != null) {
                switch(searchRequest.getPrice().getOperation()) {
                    case 1: predicates.add(cb.lessThan(root.get("price"), searchRequest.getPrice().getValue()));
                            break;
                    case 2: predicates.add(cb.greaterThan(root.get("price"), searchRequest.getPrice().getValue()));
                            break;
                    default: predicates.add(cb.equal(root.get("price"), searchRequest.getPrice().getValue()));
                            break;
                }
            }

            if (searchRequest.getQuantity()!= null && searchRequest.getQuantity().getValue() != null) {
                switch(searchRequest.getQuantity().getOperation()) {
                    case 1: predicates.add(cb.lessThan(root.get("quantity"), searchRequest.getQuantity().getValue()));
                            break;
                    case 2: predicates.add(cb.greaterThan(root.get("quantity"), searchRequest.getQuantity().getValue()));
                            break;
                    default: predicates.add(cb.equal(root.get("quantity"), searchRequest.getQuantity().getValue()));
                            break;
                }
            }

            if (searchRequest.getReleasedYear() != null && searchRequest.getReleasedYear().getValue() != null) {
                Expression<Integer> year = cb.function("year", Integer.class, root.get("releasedDate"));
                switch(searchRequest.getReleasedYear().getOperation()) {
                    case 1: predicates.add(cb.lessThan(year, searchRequest.getReleasedYear().getValue()));
                            break;
                    case 2: predicates.add(cb.greaterThan(year, searchRequest.getReleasedYear().getValue()));
                            break;
                    default: predicates.add(cb.equal(year, searchRequest.getReleasedYear().getValue()));
                            break;
                }
            } 

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public List<BookDetailsResponse> searchBooksByCriteria(SearchRequest searchRequest) {
        List<BookDetailsResponse> bookDetailsResponsesSearchResults = new ArrayList<>();
        Specification<BookDetails> specification = this.filterBy(searchRequest);
        List<BookDetails> bookDetailsSearchResults = bookDetailsRepository.findAll(specification);
        for(BookDetails bookDetails: bookDetailsSearchResults) {
            bookDetailsResponsesSearchResults.add(this.getBookDetailResponseFromBookDetailEntity(bookDetails));
        }

        return bookDetailsResponsesSearchResults;
    }


    //Conversion functions btw DTO and Entity. 
    private BookDetails getBookDetailsEntityFromBookDetailsRequestDTO(BookDetailsRequest bookDetailsRequest) {
        return new BookDetails(bookDetailsRequest.getTitle(), 
                            bookDetailsRequest.getAuthor(), 
                            bookDetailsRequest.getPrice(), 
                            bookDetailsRequest.getQuantity(), 
                            bookDetailsRequest.getLanguage(),
                            bookDetailsRequest.getGenre(),
                            bookDetailsRequest.getPageCount(),
                            bookDetailsRequest.getReleasedDate());

    }

    private BookDetailsResponse getBookDetailResponseFromBookDetailEntity(BookDetails bookDetails) {
        return new BookDetailsResponse(bookDetails.getBook_id(), 
                                    bookDetails.getTitle(), 
                                    bookDetails.getAuthor(), 
                                    bookDetails.getPrice(), 
                                    bookDetails.getQuantity(), 
                                    bookDetails.getLanguage(), 
                                    bookDetails.getGenre(),
                                    bookDetails.getPageCount(),
                                    bookDetails.getReleasedDate());    
    }
}
