package com.bookinventory.book_inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bookinventory.book_inventory.dto.messagequeues.AlertsDTO;
import com.bookinventory.book_inventory.model.LowStockAlertEntity;
import com.bookinventory.book_inventory.repository.LowStockAlertRepository;

@Service
public class AlertService {
    @Autowired
    private LowStockAlertRepository lowStockAlertRepository;

    public void saveAlertMessage(LowStockAlertEntity lowStockAlertEntity) {
        lowStockAlertRepository.save(lowStockAlertEntity);
    }

    @Cacheable(value="allAlerts")
    public List<AlertsDTO> getAllAlerts() {
        List<AlertsDTO> alerts = new ArrayList<>();
        List<LowStockAlertEntity> alertEntities = lowStockAlertRepository.findAll();
        for(LowStockAlertEntity alertEntity: alertEntities) {
            alerts.add(this.getAlertsDTOFromAlertEntity(alertEntity));
        }

        return alerts;
    }

    public long getCount() {
        return lowStockAlertRepository.count();
    }

    //Mapping functions btw Entity and DTO
    private AlertsDTO getAlertsDTOFromAlertEntity(LowStockAlertEntity lowStockAlertEntity) {
        return new AlertsDTO(lowStockAlertEntity.getBookId(), lowStockAlertEntity.getBookTitle(), lowStockAlertEntity.getMessage(), lowStockAlertEntity.getCreateAt());
    }
}
