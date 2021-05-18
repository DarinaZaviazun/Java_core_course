package main.part7.entity;

import java.util.ArrayList;
import java.util.List;

public class Filmoteka {
    private List<Film> films;

    public List<Film> getFilms(){
        if (films == null)
            films = new ArrayList<>();
        return films;
    }

    @Override
    public String toString() {
        if (films == null || films.isEmpty())
            return "No films to show";
        StringBuilder res = new StringBuilder();
        for (Film film : films)
            res.append(film).append(System.lineSeparator());
        return res.toString();
    }
}
