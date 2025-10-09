package com.bookinventory.book_inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventory.book_inventory.model.LowStockAlertEntity;
import com.bookinventory.book_inventory.repository.LowStockAlertRepository;

@Service
public class AlertService {
    @Autowired
    private LowStockAlertRepository lowStockAlertRepository;

    public void saveAlertMessage(LowStockAlertEntity lowStockAlertEntity) {
        lowStockAlertRepository.save(lowStockAlertEntity);
    }
}
