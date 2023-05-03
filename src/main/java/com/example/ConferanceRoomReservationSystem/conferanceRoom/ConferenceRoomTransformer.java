package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.conferanceRoom.ConferenceRoom;
import com.example.ConferanceRoomReservationSystem.conferanceRoom.ConferenceRoomDTO;
import org.springframework.stereotype.Component;

@Component
 class ConferenceRoomTransformer {

    ConferenceRoomDTO toDto(ConferenceRoom conferenceRoom){
        return  new ConferenceRoomDTO(
                conferenceRoom.getId(),
                conferenceRoom.getName(),
                conferenceRoom.getIdentifier(),
                conferenceRoom.getLevel(),
                conferenceRoom.isAvailable(),
                conferenceRoom.getNumOfSeats(),
                conferenceRoom.getOrganization().getName()
        );
    }
}
