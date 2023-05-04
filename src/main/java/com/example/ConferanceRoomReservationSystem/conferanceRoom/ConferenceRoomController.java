package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
class ConferenceRoomController {

    private String id;
    private String name;
    private String identifier;
    private int level;
    private boolean isAvailable;
    private int numOfSeats;
    private final ConferenceRoomService conferenceRoomService;

    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    List<ConferenceRoomDTO> getAll() {
        return conferenceRoomService.getAllConferenceRooms();
    }

    @PostMapping
    ConferenceRoomDTO add(@Validated(AddConferenceRoom.class) @RequestBody ConferenceRoomDTO conferenceRoom) {
        return conferenceRoomService.addConferenceRoom(conferenceRoom);
    }

    @DeleteMapping("/{id}")
    ConferenceRoomDTO delete(@PathVariable String id) {
        return conferenceRoomService.deleteConferenceRoom(id);
    }

    @PutMapping("/{id}")
    ConferenceRoomDTO update(@PathVariable String id, @Validated (UpdateConferenceRoom.class) @RequestBody ConferenceRoomDTO conferenceRoom) {
        return conferenceRoomService.updateConferenceRoom(id, conferenceRoom);
    }

}
