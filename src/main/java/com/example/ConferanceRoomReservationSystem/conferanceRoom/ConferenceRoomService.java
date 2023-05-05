package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import com.example.ConferanceRoomReservationSystem.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        this.conferenceRoomTransformer = conferenceRoomTransformer;
    }

    List<ConferenceRoomDTO> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll().stream()
                .map(conferenceRoomTransformer::toDto)
                .collect(Collectors.toList());
    }

    ConferenceRoomDTO getConferenceRoomById(String id) {
        return conferenceRoomRepository.findById(id)
                .map(conferenceRoomTransformer::toDto)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("No conference room found!");
                });
    }

    List<ConferenceRoomDTO> getConferenceRoomBy(
            String identifier,
            Integer level,
            boolean isAvailable,
            Integer numOfSeats,
            String organizationName
    ) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<ConferenceRoom> conferenceRoomExample = Example.of(
                new ConferenceRoom(null,
                        identifier,
                        level,
                        isAvailable,
                        numOfSeats,
                        new Organization(organizationName))
                , exampleMatcher
        );
        return conferenceRoomRepository.findAll(conferenceRoomExample).stream()
                .map(conferenceRoomTransformer::toDto)
                .collect(Collectors.toList());
    }

    ConferenceRoomDTO addConferenceRoom(ConferenceRoomDTO conferenceRoomDTO) {
        ConferenceRoom conferenceRoom = conferenceRoomTransformer.fromDto(conferenceRoomDTO);
        Organization organizationFromRepo = organizationRepository.findByName(conferenceRoom.getOrganization().getName())
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

    ConferenceRoomDTO deleteConferenceRoom(String id) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room found!"));
        conferenceRoomRepository.deleteById(id);
        return conferenceRoomTransformer.toDto(conferenceRoom);
    }

    ConferenceRoomDTO updateConferenceRoom(String id, ConferenceRoomDTO conferenceRoom) {
        return conferenceRoomTransformer.toDto(
                conferenceRoomUpdator.update(id, conferenceRoomTransformer.fromDto(conferenceRoom))
        );
    }
}
