package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.Objects;

interface AddConferenceRoom {

}

interface UpdateConferenceRoom {

}

@Entity
public class ConferenceRoom {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @NotBlank(groups = AddConferenceRoom.class)
    @Size(min = 2, max = 20, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private String name;
    @Size(min = 2, max = 20, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})

    private String identifier;

    @Min(value = 0, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    @Max(value = 10, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private int level;
    private boolean isAvailable;
    @NotNull(groups = AddConferenceRoom.class)
    @PositiveOrZero(groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private int numOfSeats;

    @ManyToOne
    private Organization organization;

    ConferenceRoom() {
    }

    public ConferenceRoom(String id, String name, String identifier, int level, boolean isAvailable, int numOfSeats, Organization organization) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numOfSeats = numOfSeats;
        this.organization = organization;
    }

    public ConferenceRoom(String name, String identifier, int level, boolean isAvailable, int numOfSeats, Organization organization) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConferenceRoom that = (ConferenceRoom) o;
        return level == that.level && isAvailable == that.isAvailable && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(identifier, that.identifier) && Objects.equals(numOfSeats, that.numOfSeats) && Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identifier, level, isAvailable, numOfSeats, organization);
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
                '}';
    }
}
