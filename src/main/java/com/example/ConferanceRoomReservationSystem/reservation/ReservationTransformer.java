package com.example.ConferanceRoomReservationSystem.reservation;

import com.example.ConferanceRoomReservationSystem.conferanceRoom.ConferenceRoom;
import org.springframework.stereotype.Component;

@Component
public class ReservationTransformer {
    ReservationDto toDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setReservationName(reservation.getReservationName());
        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setEndDate(reservation.getEndDate());
        reservationDto.setConferenceRoomId(reservation.getConferenceRoom().getId());
        return reservationDto;
    }

    Reservation fromDto(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setEndDate(reservationDto.getEndDate());
        reservation.setReservationName(reservationDto.getReservationName());
        reservation.setConferenceRoom(new ConferenceRoom(reservationDto.getId()));
        return reservation;
    }
}
