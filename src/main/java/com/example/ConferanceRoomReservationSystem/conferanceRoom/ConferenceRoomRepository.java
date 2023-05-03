package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, String> {
    Optional<ConferenceRoom> findByName(String name);

    Optional<ConferenceRoom> findByNameAndOrganization_Name(String name, String organizationName);

}