package com.labtrack.items.interfaces.rest.transform;

import com.labtrack.items.domain.model.commands.CreateEquipmentCommand;
import com.labtrack.items.interfaces.rest.resources.CreateEquipmentResource;

public class CreateEquipmentCommandFromResourceAssembler {

    public static CreateEquipmentCommand toCommand(CreateEquipmentResource resource) {
        return new CreateEquipmentCommand(
                resource.name(),
                resource.serialNumber(),
                resource.location()
        );
    }
}