package com.example.ConferanceRoomReservationSystem.organization;

import com.example.ConferanceRoomReservationSystem.SortType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
class OrganizationController {
    private final OrganizationService organizationService;

    OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    List<OrganizationDTO> getAll(@RequestParam(defaultValue = "ASC") SortType sortType) {
        return organizationService.getAllOrganizations(sortType);
    }

    @GetMapping("/{name}")
    OrganizationDTO getById(@PathVariable String name) {
        return organizationService.getOrganization(name);
    }

    @PostMapping
    OrganizationDTO add(@Validated(value = AddOrganization.class) @RequestBody OrganizationDTO organization) {
        return organizationService.addOrganization(organization);
    }

    @PutMapping("/{name}")
    OrganizationDTO update(@PathVariable String name, @Validated(value = UpdateOrganization.class) @RequestBody OrganizationDTO organization) {
        return organizationService.updateOrganization(name, organization);
    }

    @DeleteMapping("/{name}")
    OrganizationDTO delete(@PathVariable String name) {
        return organizationService.deleteOrganization(name);
    }

}
