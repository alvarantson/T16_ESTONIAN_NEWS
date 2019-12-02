package oop.ajalehed;

import oop.ajaleht_logic.Postimehelised;

import java.time.LocalDate;
import java.util.List;

public class Postimees extends Postimehelised {

    public Postimees() {
        super("https://www.postimees.ee/search", "Postimees");
    }
}
