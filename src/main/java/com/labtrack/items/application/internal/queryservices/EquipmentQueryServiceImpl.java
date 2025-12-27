package com.labtrack.items.application.internal.queryservices;

import com.labtrack.items.domain.model.aggregates.Equipment;
import com.labtrack.items.domain.model.valueobjects.EquipmentId;
import com.labtrack.items.domain.model.valueobjects.EquipmentStatus;
import com.labtrack.items.domain.services.EquipmentQueryService;
import com.labtrack.items.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentQueryServiceImpl implements EquipmentQueryService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentQueryServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> handleGetAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> handleGetById(EquipmentId id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public List<Equipment> handleFilterByName(String name) {
        return equipmentRepository.findByName_ValueContainingIgnoreCase(name);
    }

    @Override
    public List<Equipment> handleSearchByStatus(String status) {
        return equipmentRepository.findByStatus(EquipmentStatus.ACTIVE);

    }
}