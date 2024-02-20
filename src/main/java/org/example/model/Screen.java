package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Screen {
    String screenId;
    List<Seat> seats = new ArrayList<>(); // totalSeats;

    @Override
    public String toString() {
        return "Screen{" +
                "screenId='" + screenId + '\'' +
                '}';
    }
}
