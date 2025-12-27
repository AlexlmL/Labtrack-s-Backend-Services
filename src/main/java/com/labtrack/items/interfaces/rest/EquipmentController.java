package com.labtrack.items.interfaces.rest;

import com.labtrack.items.application.internal.commandservices.EquipmentCommandServiceImpl;
import com.labtrack.items.application.internal.queryservices.EquipmentQueryServiceImpl;
import com.labtrack.items.domain.model.aggregates.Equipment;
import com.labtrack.items.domain.model.commands.DeleteEquipmentCommand;
import com.labtrack.items.domain.model.queries.*;
import com.labtrack.items.domain.model.valueobjects.EquipmentId;
import com.labtrack.items.domain.model.valueobjects.EquipmentStatus;
import com.labtrack.items.interfaces.rest.resources.*;
import com.labtrack.items.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentCommandServiceImpl commandService;
    private final EquipmentQueryServiceImpl queryService;

    public EquipmentController(
            EquipmentCommandServiceImpl commandService,
            EquipmentQueryServiceImpl queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }
    @PostMapping
    public ResponseEntity<EquipmentResource> createEquipment(
            @RequestBody CreateEquipmentResource resource) {

        var command = CreateEquipmentCommandFromResourceAssembler.toCommand(resource);

        Equipment equipment = commandService.handle(command);

        return ResponseEntity.ok(EquipmentResourceAssembler.toResource(equipment));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResource> updateEquipment(
            @PathVariable String id,
            @RequestBody UpdateEquipmentResource resource) {

        var command = UpdateEquipmentCommandFromResourceAssembler.toCommand(id, resource);

        Equipment equipment = commandService.handle(command);

        return ResponseEntity.ok(EquipmentResourceAssembler.toResource(equipment));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<EquipmentResource> changeStatus(
            @PathVariable String id,
            @RequestBody ChangeStatusResource resource) {

        var command = ChangeStatusCommandFromResourceAssembler.toCommand(id, resource);

        Equipment equipment = commandService.handle(command);

        return ResponseEntity.ok(EquipmentResourceAssembler.toResource(equipment));
    }
    @PostMapping("/{id}/maintenance")
    public ResponseEntity<EquipmentResource> registerMaintenance(
            @PathVariable String id,
            @RequestBody RegisterMaintenanceResource resource) {

        EquipmentId equipmentId = EquipmentId.of(id);

        var command = RegisterMaintenanceCommandFromResourceAssembler.toCommand(equipmentId, resource);

        Equipment equipment = commandService.handle(command);

        return ResponseEntity.ok(EquipmentResourceAssembler.toResource(equipment));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {

        var equipmentId = new EquipmentId(UUID.fromString(id));

        commandService.handle(new DeleteEquipmentCommand(equipmentId));

        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<EquipmentResource>> getAllEquipment(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status
    ) {

        List<Equipment> result;

        if (status != null && !status.isBlank()) {
            try {
                EquipmentStatus equipmentStatus =
                        EquipmentStatus.valueOf(status.trim().toUpperCase());

                result = queryService.handleSearchByStatus(equipmentStatus);

            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }

        } else if (name != null && !name.isBlank()) {
            result = queryService.handleFilterByName(name);

        } else {
            result = queryService.handleGetAll();
        }

        var resources = result.stream()
                .map(EquipmentResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResource> getById(@PathVariable String id) {

        var equipmentId = new EquipmentId(UUID.fromString(id));

        var optionalEquipment = queryService.handleGetById(equipmentId);

        var equipment = optionalEquipment
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        return ResponseEntity.ok(
                EquipmentResourceAssembler.toResource(equipment)
        );
    }


}