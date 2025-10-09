package com.bookinventory.book_inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventory.book_inventory.model.LowStockAlertEntity;

public interface LowStockAlertRepository  extends JpaRepository<LowStockAlertEntity, Integer>{
 
} 