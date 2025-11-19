package com.bookinventory.book_inventory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookinventory.book_inventory.dto.ai.BookFeaturesDTO;
import com.bookinventory.book_inventory.model.BookDetails;

@Service
public class AIService {
    @Autowired
    private WebClient webClient;

    public List<Double> getBestSellerProbabilities(List<BookDetails> books) {
        List<BookFeaturesDTO> dtoList = books.stream()
        .map(b -> new BookFeaturesDTO(
            b.getTitle(),
            b.getAuthor(),
            b.getGenre(),
            b.getPageCount(),
            b.getLanguage(),
            b.getReleasedDate().getYear()
        ))
        .toList();

        System.out.println("Printing the list : " + dtoList);
        try {
            return webClient.post()
                .uri("http://localhost:8000/predict_best_seller")
                .bodyValue(dtoList)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Double>>() {})
                .block();
        } catch (Exception e) {
            System.out.println("AI Service Unavailable" + e.getMessage());
            return dtoList.stream().map(x -> 0.0).toList();
        }  
    }
}
