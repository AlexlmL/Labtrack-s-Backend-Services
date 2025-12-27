package com.labtrack.items.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.List;

public record EquipmentResource(
        String id,
        String name,
        String serialNumber,
        String location,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<MaintenanceRecordResource> maintenanceHistory
) {}