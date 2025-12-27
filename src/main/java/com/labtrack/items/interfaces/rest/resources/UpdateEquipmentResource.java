package com.labtrack.items.interfaces.rest.resources;

public record UpdateEquipmentResource(
        String name,
        String serialNumber,
        String location
) {}