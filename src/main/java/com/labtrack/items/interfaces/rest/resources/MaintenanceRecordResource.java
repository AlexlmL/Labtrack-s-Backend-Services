package com.labtrack.items.interfaces.rest.resources;

import java.time.LocalDateTime;

public record MaintenanceRecordResource(
        String id,
        String description,
        String performedBy,
        LocalDateTime date
) {}