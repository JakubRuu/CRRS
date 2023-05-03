package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import com.example.ConferanceRoomReservationSystem.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
class ConferenceRoomService {
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationRepository organizationRepository;
    private final ConferenceRoomUpdator conferenceRoomUpdator;
    private final ConferenceRoomTransformer conferenceRoomTransformer;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository,
                                 OrganizationRepository organizationRepository,
                                 ConferenceRoomUpdator conferenceRoomUpdator,
                                 ConferenceRoomTransformer conferenceRoomTransformer) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationRepository;
        this.conferenceRoomUpdator = conferenceRoomUpdator;
        this.conferenceRoomTransformer=conferenceRoomTransformer;
    }

    List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll();
    }

    ConferenceRoomDTO addConferenceRoom(ConferenceRoom conferenceRoom) {
        String organizationName = conferenceRoom.getOrganization().getName();
        Organization organizationFromRepo = organizationRepository.findByName(organizationName)
                .orElseThrow(() -> {
                    throw new NoSuchElementException();
                });
        conferenceRoom.setOrganization(organizationFromRepo);
        conferenceRoomRepository.findByNameAndOrganization_Name(conferenceRoom.getName(), conferenceRoom.getOrganization().getName())
                .ifPresent(cr -> {
                    throw new IllegalArgumentException("Conference room already exists!");
                });
        return conferenceRoomTransformer.toDto(conferenceRoomRepository.save(conferenceRoom));
    }

    ConferenceRoom deleteConferenceRoom(String id) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room found!"));
        conferenceRoomRepository.deleteById(id);
        return conferenceRoom;
    }

    ConferenceRoom updateConferenceRoom(String id, ConferenceRoom conferenceRoom) {
        return conferenceRoomUpdator.update(id, conferenceRoom);
    }
}
