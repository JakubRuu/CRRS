package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import com.example.ConferanceRoomReservationSystem.reservation.Reservation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class ConferenceRoom {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String name;
    private String identifier;
    private Integer level;
    private boolean isAvailable;
    private Integer numOfSeats;
    @ManyToOne
    private Organization organization;
    @OneToMany(mappedBy = "conferenceRoom")
    private List<Reservation> reservations;

    ConferenceRoom() {
    }

    public ConferenceRoom(String id) {
        this.id = id;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level
            , boolean isAvailable, Integer numOfSeats
            , Organization organization
            , List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numOfSeats = numOfSeats;
        this.organization = organization;
        this.reservations = reservations;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level, boolean isAvailable,
                          Integer numOfSeats, Organization organization) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numOfSeats = numOfSeats;
        this.organization = organization;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level, boolean isAvailable, Integer numOfSeats) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numOfSeats = numOfSeats;
    }

    public ConferenceRoom(String name, String identifier, Integer level, boolean isAvailable, Integer numOfSeats, Organization organization) {
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numOfSeats = numOfSeats;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConferenceRoom that = (ConferenceRoom) o;
        return isAvailable == that.isAvailable && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(identifier, that.identifier) && Objects.equals(level, that.level) && Objects.equals(numOfSeats, that.numOfSeats) && Objects.equals(organization, that.organization) && Objects.equals(reservations, that.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identifier, level, isAvailable, numOfSeats, organization, reservations);
    }

    @Override
    public String toString() {
        return "ConferenceRoom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", level=" + level +
                ", isAvailable=" + isAvailable +
                ", numOfSeats=" + numOfSeats +
                ", organization=" + organization +
                ", reservations=" + reservations +
                '}';
    }
}
