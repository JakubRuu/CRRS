package com.example.ConferanceRoomReservationSystem.conferanceRoom;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;
    private String id;
    private String name;
    private String identifier;
    private Integer level;
    private Boolean isAvailable;
    private Integer numOfSeats;

    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    List<ConferenceRoomDTO> getAll(
            @RequestParam(required = false) String identifier,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String organizationName,
            @RequestParam(required = false) boolean isAvailable,
            @RequestParam(required = false) Integer numOfSeats
    ) {
        return conferenceRoomService.getConferenceRoomBy(identifier, level, isAvailable, numOfSeats, organizationName);
    }

    @GetMapping("/{id}")
    ConferenceRoomDTO getById(String id) {
        return conferenceRoomService.getConferenceRoomById(id);
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
    ConferenceRoomDTO update(@PathVariable String id, @Validated(UpdateConferenceRoom.class) @RequestBody ConferenceRoomDTO conferenceRoom) {
        return conferenceRoomService.updateConferenceRoom(id, conferenceRoom);
    }

}
