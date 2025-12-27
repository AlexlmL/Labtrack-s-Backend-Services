package com.labtrack.items.interfaces.rest.transform;

import com.labtrack.items.domain.model.commands.UpdateEquipmentCommand;
import com.labtrack.items.interfaces.rest.resources.UpdateEquipmentResource;

public class UpdateEquipmentCommandFromResourceAssembler {

    public static UpdateEquipmentCommand toCommand(String id, UpdateEquipmentResource resource) {
        return new UpdateEquipmentCommand(
                id,
                resource.name(),
                resource.serialNumber(),
                resource.location()
        );
    }
}