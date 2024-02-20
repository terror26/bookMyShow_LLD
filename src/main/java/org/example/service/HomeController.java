package org.example.service;

import org.example.model.Theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeController { // gets theatre and its show details

    Map<String, List<Theatre>> cityVsTheatres = new HashMap<>();

    public List<Theatre> getTheaterForCity(String city) {
        return cityVsTheatres.get(city);
    }

    public List<Theatre> allTheatre() {
        return cityVsTheatres.entrySet().stream().map((k) -> k.getValue()).flatMap(list -> list.stream()).collect(Collectors.toList());
    }

    public void addTheatre(Theatre theatre, String city) {
        if(cityVsTheatres.get(city) == null) {
            cityVsTheatres.put(city, new ArrayList<>());
        }
        cityVsTheatres.get(city).add(theatre);
    }
}
