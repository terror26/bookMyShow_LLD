package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Show {
    String showId;
    String timing;
    List<SeatDTO> seats;

    @Override
    public String toString() {
        return "Show{" +
                "showId='" + showId + '\'' +
                '}';
    }
}
