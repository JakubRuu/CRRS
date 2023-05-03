package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import com.example.ConferanceRoomReservationSystem.organization.OrganizationRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ConferenceRoomUpdator {
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationRepository organizationRepository;

    public ConferenceRoomUpdator(ConferenceRoomRepository conferenceRoomRepository, OrganizationRepository organizationRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationRepository;
    }

    ConferenceRoom update(String id, ConferenceRoom conferenceRoom) {
        ConferenceRoom conferenceRoomToUpdate = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room to update found!"));

        boolean isNameUpdated= updateName(conferenceRoomToUpdate, conferenceRoom);
        updateAvailability(conferenceRoomToUpdate, conferenceRoom);
        updateIdentifier(conferenceRoomToUpdate, conferenceRoom);
        updateNumberOfSeats(conferenceRoomToUpdate, conferenceRoom);
        updateLevel(conferenceRoomToUpdate, conferenceRoom);
       boolean isOrganizationUpdated= updateOrganization(conferenceRoomToUpdate, conferenceRoom);
        checkIfConferenceRoomIsunique(conferenceRoomToUpdate,isOrganizationUpdated,isNameUpdated);
        return conferenceRoomRepository.save(conferenceRoomToUpdate);
    }

    private void checkIfConferenceRoomIsunique(ConferenceRoom conferenceRoomToUpdate,
                                               boolean isOrganizationUpdated ,
                                               boolean isNameUpdated) {

        if (!isNameUpdated && !isOrganizationUpdated){
            return;
        }
        conferenceRoomRepository.findByNameAndOrganization_Name(
                        conferenceRoomToUpdate.getName(),
                        conferenceRoomToUpdate.getOrganization().getName())
                .ifPresent(cr -> {
                    throw new IllegalArgumentException("Conference room already exists!");
                });
    }

    private boolean updateName(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        String name = conferenceRoom.getName();
        if (conferenceRoom.getName() != null) {
            conferenceRoomToUpdate.setName(name);
             return true;
        }
        return false;
    }

    private void updateAvailability(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        Boolean isAvailable = conferenceRoom.isAvailable();
        if (isAvailable != null) {
            conferenceRoomToUpdate.setAvailable(isAvailable);

        }
    }

    private void updateIdentifier(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        String identifire = conferenceRoom.getIdentifier();
        if (identifire != null) {
            conferenceRoomToUpdate.setIdentifier(identifire);
        }
    }

    private void updateNumberOfSeats(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        Integer numberOfSeats = conferenceRoom.getNumOfSeats();
        if (numberOfSeats != 0) {
            conferenceRoomToUpdate.setNumOfSeats(numberOfSeats);
        }
    }

    private void updateLevel(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        int level = conferenceRoom.getLevel();
        if (level != 0) {
            conferenceRoomToUpdate.setLevel(level);
        }
    }

    private boolean updateOrganization(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        Organization organization = conferenceRoom.getOrganization();
        if (organization != null) {
          Organization organizationFromRepo=  organizationRepository.findByName(organization.getName())
                    .orElseThrow(() -> new NoSuchElementException("No organization found!"));
            conferenceRoomToUpdate.setOrganization(organizationFromRepo);
        return true;
        }
        return false;
    }
}