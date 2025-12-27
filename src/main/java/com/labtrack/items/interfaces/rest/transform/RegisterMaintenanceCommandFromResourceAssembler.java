package com.labtrack.items.interfaces.rest.transform;

import com.labtrack.items.domain.model.commands.RegisterMaintenanceCommand;
import com.labtrack.items.domain.model.valueobjects.EquipmentId;
import com.labtrack.items.interfaces.rest.resources.RegisterMaintenanceResource;

public class RegisterMaintenanceCommandFromResourceAssembler {

    public static RegisterMaintenanceCommand toCommand(        EquipmentId id, RegisterMaintenanceResource resource){
        return new RegisterMaintenanceCommand(
                id,
                resource.description(),
                resource.performedBy()
        );
    }
}