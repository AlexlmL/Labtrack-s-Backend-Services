package com.labtrack.items.interfaces.rest.transform;

import com.labtrack.items.domain.model.commands.ChangeEquipmentStatusCommand;
import com.labtrack.items.interfaces.rest.resources.ChangeStatusResource;

public class ChangeStatusCommandFromResourceAssembler {

    public static ChangeEquipmentStatusCommand toCommand(String id, ChangeStatusResource resource) {
        return new ChangeEquipmentStatusCommand(
                id,
                resource.newStatus()
        );
    }
}