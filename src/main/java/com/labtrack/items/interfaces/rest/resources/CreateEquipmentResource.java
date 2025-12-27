package com.labtrack.items.interfaces.rest.resources;

public record CreateEquipmentResource(
        String name,
        String serialNumber,
        String location
) {}