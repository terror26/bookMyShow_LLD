package org.example.service;

import org.example.model.SeatDTO;
import org.example.model.Theatre;

import java.util.List;
import java.util.Optional;


public class BookingController {
    SeatController seatController = new SeatController();
    public List<SeatDTO> getShowSeats(Theatre theatre, String showId) {
        return seatController.getFreeSeats(theatre,showId);
    }

    // Threading control here
    public boolean bookSeat(Theatre theatre, String showId, String seatId) {
        Optional<SeatDTO> seatDTO = seatController.getSeatDTO(theatre, showId, seatId);
        if(seatDTO.isPresent() && seatDTO.get().isTaken() == false) { // if not taken
            // can be booked;
            synchronized (this) {
                if(seatDTO.get().isTaken()) { // double check
                    return false;
                } else {
                    seatDTO.get().setTaken(true);
                    return true;
                }
            }
        }
        return false; // cant book this
    }
}
