package com.bookinventory.book_inventory.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventory.book_inventory.dto.BookDetailsRequest;
import com.bookinventory.book_inventory.dto.BookDetailsResponse;
import com.bookinventory.book_inventory.dto.alerts.AlertsDTO;
import com.bookinventory.book_inventory.dto.filter.SearchRequest;
import com.bookinventory.book_inventory.service.AlertService;
import com.bookinventory.book_inventory.service.BookDetailsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/book_details")
public class BookDetailsRestController {

    @Autowired
    private BookDetailsService bookDetailsService;

    @Autowired
    private AlertService alertService;

    @GetMapping
    public List<BookDetailsResponse> getAllBookDetails() {
        return bookDetailsService.getAllBookDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsResponse> findBookDetailsById(@PathVariable int id) {
      BookDetailsResponse bookDetailsResponse = bookDetailsService.getBookDetailsById(id);
      return ResponseEntity.ok(bookDetailsResponse);
    }

    @PostMapping
    public ResponseEntity<BookDetailsResponse> addBookDetails(@RequestBody BookDetailsRequest bookDetailsRequest) {
        BookDetailsResponse bookDetailsResponse = this.bookDetailsService.addBookDetails(bookDetailsRequest);
        URI location = URI.create("/api/book_details/" + bookDetailsResponse.getId());
        return ResponseEntity.created(location).body(bookDetailsResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDetailsResponse> updateBookDetails(@PathVariable int id, @RequestBody BookDetailsRequest bookDetailsRequest) {
        BookDetailsResponse bookDetailsResponse = bookDetailsService.updateBookDetailsById(id, bookDetailsRequest);
        return ResponseEntity.ok(bookDetailsResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookDetailById(@PathVariable int id) {
        bookDetailsService.deleteBookDetailById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<BookDetailsResponse>> searchBasedCriteria(@RequestBody SearchRequest searchRequest) {
        List<BookDetailsResponse> bookDetailsResponses = bookDetailsService.searchBooksByCriteria(searchRequest);
        return ResponseEntity.ok(bookDetailsResponses);

    }

    @GetMapping("/alerts")
    public ResponseEntity<List<AlertsDTO>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/alerts/count")
    public ResponseEntity<Long> getTotalAlertsCount() {
        Long count = alertService.getCount();
        return ResponseEntity.ok(count);
    }
}
