package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Seat;
import org.example.model.SeatDTO;
import org.example.model.Show;
import org.example.model.Theatre;
import org.example.service.BookingController;
import org.example.service.HomeController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Driver {
    public void testBookingSystem() throws InterruptedException {
        Theatre pvr1 = new Theatre();
        Theatre pvr2 = new Theatre();
        Theatre pvr3 = new Theatre();

        //
        List<SeatDTO> seats = new ArrayList<>();
        for(int i = 0; i< 20;i++){
            seats.add(SeatDTO.builder().seat(Seat.builder().seatId(String.valueOf(i)).build()).isTaken(false).build());
        }

        List<Show> shows = new ArrayList<>();
        shows.add(Show.builder().showId("Kal Ho na Ho").seats(new ArrayList<>(seats)).build()); // all seats free
        shows.add(Show.builder().showId("Mai Hu Na").seats(new ArrayList<>(seats)).build()); // all seats free

        //pvr1
        pvr1.setId("pvr1");
        pvr1.setShows(new ArrayList<>(shows));

        //pvr1
        pvr2.setId("pvr2");
        pvr2.setShows(new ArrayList<>(shows));

        //pvr1
        pvr3.setId("pvr3");
        pvr3.setShows(new ArrayList<>(shows));


        HomeController homeController = new HomeController();
        homeController.addTheatre(pvr1,"DELHI");
        homeController.addTheatre(pvr2,"BLR");
        homeController.addTheatre(pvr3,"CHENNAI");

        log.info(Arrays.toString(homeController.getTheaterForCity("DELHI").toArray()));

        // select "Mai Hu Na"
        BookingController bookingController = new BookingController();
        log.info(Arrays.toString(bookingController.getShowSeats(pvr1,"Kal Ho na Ho").toArray()));

        // concurrent booking
        for(int i = 0;i<20;i++) {
            ConcurrentUserThread r1 = new ConcurrentUserThread(bookingController,String.valueOf(i),pvr1,"Kal Ho na Ho");
            ConcurrentUserThread r2 = new ConcurrentUserThread(bookingController,String.valueOf(i),pvr1,"Kal Ho na Ho");

            Thread t1 = new Thread(r1);
            t1.setName("t1");
            Thread t2 = new Thread(r2);
            t2.setName("t2");
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            log.info("---- ");
        }

    }
}
