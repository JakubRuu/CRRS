package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
class ConferenceRoomService {
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository, OrganizationRepository organizationRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationRepository;
    }

    List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll();
    }

    ConferenceRoom addConferenceRoom(ConferenceRoom conferenceRoom) {
        conferenceRoomRepository.findByNameAndOrganization_Name(conferenceRoom.getName(), conferenceRoom.getOrganization().getName())
                .ifPresent(cr -> {
                    throw new IllegalArgumentException("Conference room already exists!");
                });
        organizationRepository.findByName(conferenceRoom.getOrganization().getName())
                .orElseThrow(() -> new NoSuchElementException("No organization found!"));
        return conferenceRoomRepository.save(conferenceRoom);
    }

    ConferenceRoom deleteConferenceRoom(String id) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room found!"));
        conferenceRoomRepository.deleteById(id);
        return conferenceRoom;
    }
}
