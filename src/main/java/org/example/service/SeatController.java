package org.example.service;

import org.example.model.Seat;
import org.example.model.SeatDTO;
import org.example.model.Show;
import org.example.model.Theatre;

import java.util.List;
import java.util.Optional;

public class SeatController {
    List<SeatDTO> getFreeSeats(Theatre theatre, String showId) { // returned total seats
        Optional<Show> show = theatre.getShows().stream().filter(s -> showId.equals(s.getShowId())).findFirst();
        if(show.isPresent()) {
            return show.get().getSeats();
        }
        return null;
    }

    public Optional<SeatDTO> getSeatDTO(Theatre theatre, String showId, String seatId) {
        List<SeatDTO> seats = getFreeSeats(theatre, showId);
        return seats.stream().filter(seatDto->seatId.equals(seatDto.getSeat().getSeatId())).findFirst();
    }
}
