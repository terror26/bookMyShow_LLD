package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Theatre {
    String id; // currently same as name
    List<Screen> screens = new ArrayList<>();
    List<Show> shows = new ArrayList<>();

    @Override
    public String toString() {
        return "Theatre{" +
                "id='" + id + '\'' +
                ", shows=" + shows +
                '}';
    }
}
