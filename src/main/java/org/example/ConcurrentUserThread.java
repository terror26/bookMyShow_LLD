package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Theatre;
import org.example.service.BookingController;

@Slf4j
public class ConcurrentUserThread implements Runnable {
    private BookingController bookingController;
    private String seatId;
    private Theatre theatre;
    private String showId;

    public ConcurrentUserThread(BookingController bookingController, String seatId, Theatre theatre, String showId) {
        this.bookingController = bookingController;
        this.seatId = seatId;
        this.theatre = theatre;
        this.showId = showId;
    }

    @Override
    public void run() {
        boolean bookedSuccessfully = bookingController.bookSeat(theatre, showId, seatId);
        log.info("booking Status = " + bookedSuccessfully + " by thread " + Thread.currentThread() + " for seatID " + seatId);
    }
}
