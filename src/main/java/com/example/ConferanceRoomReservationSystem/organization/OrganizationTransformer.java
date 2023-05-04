package com.example.ConferanceRoomReservationSystem.organization;

import com.example.ConferanceRoomReservationSystem.conferanceRoom.ConferenceRoomTransformer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
class OrganizationTransformer {
    private final ConferenceRoomTransformer conferenceRoomTransformer;

    public OrganizationTransformer(ConferenceRoomTransformer conferenceRoomTransformer) {
        this.conferenceRoomTransformer = conferenceRoomTransformer;
    }

    OrganizationDTO toDto(Organization organization) {
        return new OrganizationDTO(
                organization.getId(),
                organization.getName(),
                organization.getDescription(),
                organization.getConferenceRooms().stream()
                        .map(conferenceRoomTransformer::toDto)
                        .collect(Collectors.toList())
        );
    }

    Organization fromDto(OrganizationDTO organizationDTO) {
        return new Organization(
                organizationDTO.getId(),
                organizationDTO.getName(),
                organizationDTO.getDescription(),
                organizationDTO.getConferenceRooms().stream()
                        .map(conferenceRoomTransformer::fromDto)
                        .collect(Collectors.toList())
        );
    }
}
