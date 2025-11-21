package com.bookinventory.book_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookInventoryApplication.class, args);
	}

}
