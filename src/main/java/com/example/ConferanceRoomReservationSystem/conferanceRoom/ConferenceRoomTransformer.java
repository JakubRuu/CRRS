package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomTransformer {
    public ConferenceRoomDTO toDto(ConferenceRoom conferenceRoom) {
        return new ConferenceRoomDTO(
                conferenceRoom.getId(),
                conferenceRoom.getName(),
                conferenceRoom.getIdentifier(),
                conferenceRoom.getLevel(),
                conferenceRoom.isAvailable(),
                conferenceRoom.getNumOfSeats(),
                conferenceRoom.getOrganization().getName()
        );
    }

    public ConferenceRoom fromDto(ConferenceRoomDTO conferenceRoomDTO) {
        return new ConferenceRoom(
                conferenceRoomDTO.getId(),
                conferenceRoomDTO.getName(),
                conferenceRoomDTO.getIdentifier(),
                conferenceRoomDTO.getLevel(),
                conferenceRoomDTO.isAvailable(),
                conferenceRoomDTO.getNumOfSeats(),
                new Organization(conferenceRoomDTO.getOrganization())
        );
    }
}
