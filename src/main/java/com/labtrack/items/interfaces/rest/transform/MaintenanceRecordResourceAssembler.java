package com.labtrack.items.interfaces.rest.transform;

import com.labtrack.items.domain.model.entities.MaintenanceRecord;
import com.labtrack.items.interfaces.rest.resources.MaintenanceRecordResource;

public class MaintenanceRecordResourceAssembler {

    public static MaintenanceRecordResource toResource(MaintenanceRecord record) {
        return new MaintenanceRecordResource(
                record.getId().toString(),
                record.getDescription(),
                record.getPerformedBy(),
                record.getDate()
        );
    }
}