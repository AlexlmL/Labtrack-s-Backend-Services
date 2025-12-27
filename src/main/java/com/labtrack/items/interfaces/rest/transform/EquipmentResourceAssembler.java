package com.labtrack.items.interfaces.rest.transform;

import com.labtrack.items.domain.model.aggregates.Equipment;
import com.labtrack.items.interfaces.rest.resources.*;

import java.util.stream.Collectors;

public class EquipmentResourceAssembler {

    public static EquipmentResource toResource(Equipment equipment) {

        return new EquipmentResource(
                equipment.getId().getValue().toString(),
                equipment.getName().getValue(),
                equipment.getSerialNumber().getValue(),
                equipment.getLocation().getValue(),
                equipment.getStatus().name(),
                equipment.getCreatedAt(),
                equipment.getUpdatedAt(),
                equipment.getMaintenanceHistory()
                        .stream()
                        .map(m -> new MaintenanceRecordResource(
                                m.getId().toString(),
                                m.getDescription(),
                                m.getPerformedBy(),
                                m.getDate()
                        ))
                        .collect(Collectors.toList())
        );
    }
}