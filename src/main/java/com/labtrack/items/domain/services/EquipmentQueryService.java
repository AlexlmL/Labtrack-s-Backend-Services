package com.labtrack.items.domain.services;

import com.labtrack.items.domain.model.aggregates.Equipment;
import com.labtrack.items.domain.model.valueobjects.EquipmentId;
import com.labtrack.items.domain.model.valueobjects.EquipmentStatus;

import java.util.List;
import java.util.Optional;

public interface EquipmentQueryService {
    List<Equipment> handleGetAll();
    Optional<Equipment> handleGetById(EquipmentId id);
    List<Equipment> handleFilterByName(String name);
    List<Equipment> handleSearchByStatus(EquipmentStatus status);
}
