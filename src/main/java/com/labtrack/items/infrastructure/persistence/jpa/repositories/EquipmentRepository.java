package com.labtrack.items.infrastructure.persistence.jpa.repositories;

import com.labtrack.items.domain.model.aggregates.Equipment;
import com.labtrack.items.domain.model.valueobjects.EquipmentId;
import com.labtrack.items.domain.model.valueobjects.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, EquipmentId> {
    Optional<Equipment> findById(EquipmentId id);
    List<Equipment> findByName_ValueContainingIgnoreCase(String name);
    List<Equipment> findByStatus(EquipmentStatus status);
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, String id);
}