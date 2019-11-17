package oop;

import oop.ajalehed.Postimees;
import oop.kaapimine.ArtiklidFaili;
import oop.objects.Ajaleht;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeiaArtiklid {

    private static List<Ajaleht> ajalehed = Arrays.asList(new Postimees()
            //,new Õhtuleht(), new Elu24(), new Telegram(),
            //new Delfi(), new AnneJaStiil(), new Kroonika()
    );

    private static List<String> keelatud = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArtiklidFaili faili = new ArtiklidFaili();
        faili.sisse(ajalehed, keelatud);
    }
}
