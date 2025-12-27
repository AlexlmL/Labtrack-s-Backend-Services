package com.labtrack.items.application.internal.commandservices;

import com.labtrack.items.domain.model.aggregates.Equipment;
import com.labtrack.items.domain.model.commands.*;
import com.labtrack.items.domain.model.entities.MaintenanceRecord;
import com.labtrack.items.domain.model.valueobjects.*;
import com.labtrack.items.domain.services.EquipmentCommandService;
import com.labtrack.items.domain.services.EquipmentMaintenanceService;
import com.labtrack.items.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentCommandServiceImpl implements EquipmentCommandService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMaintenanceService maintenanceService;

    public EquipmentCommandServiceImpl(
            EquipmentRepository equipmentRepository,
            EquipmentMaintenanceService maintenanceService
    ) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceService = maintenanceService;
    }

    @Override
    public Equipment handle(CreateEquipmentCommand command) {

        Equipment equipment = Equipment.create(
                new EquipmentName(command.name()),
                new SerialNumber(command.serialNumber()),
                new LabLocation(command.location())
        );


        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment handle(UpdateEquipmentCommand command) {

        Equipment equipment = equipmentRepository.findById(EquipmentId.of(command.id()))
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        equipment.updateInfo(
                new EquipmentName(command.name()),
                new SerialNumber(command.serialNumber()),
                new LabLocation(command.location())
        );

        return equipmentRepository.save(equipment);
    }

    @Override
    public void handle(DeleteEquipmentCommand command) {

        if (!equipmentRepository.existsById(command.equipmentId())) {
            throw new RuntimeException("Equipment not found");
        }

        equipmentRepository.deleteById(command.equipmentId());
    }

    @Override
    public Equipment handle(ChangeEquipmentStatusCommand command) {

        Equipment equipment = equipmentRepository.findById(EquipmentId.of(command.equipmentId()))
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        EquipmentStatus newStatus = EquipmentStatus.valueOf(command.newStatus());

        maintenanceService.changeStatus(equipment, newStatus);

        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment handle(RegisterMaintenanceCommand command) {

        Equipment equipment = equipmentRepository.findById(command.equipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        MaintenanceRecord record = new MaintenanceRecord(
                command.description(),
                command.performedBy()
        );

        maintenanceService.registerMaintenance(equipment, record);

        return equipmentRepository.save(equipment);
    }
}