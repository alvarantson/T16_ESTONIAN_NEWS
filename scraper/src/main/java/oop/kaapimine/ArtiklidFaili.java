package oop.kaapimine;

import oop.objects.Ajaleht;
import oop.objects.Artikkel;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtiklidFaili {

    private Map<String, Integer> globalList;

    public Map<String, Integer> getGlobalList() {
        return globalList;
    }

    public ArtiklidFaili() {
        this.globalList = new HashMap<>();
    }

    public static void kirjuta(List<Artikkel> artiklid, List<String> keelatud) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter("artiklid.csv")) {
            for (Artikkel artikkel : artiklid) {
                pw.println(artikkel.getAeg() + "\t" +
                        artikkel.getLink() + "\t" +
                        artikkel.getPortaal() + "\t" +
                        artikkel.getPealkiri() + "\t" +
                        artikkel.getTekst().replace("\n", "") + "\t" +
                        artikkel.getSektsioon() + "\t" +
                        artikkel.getAutor() + "\t" +
                        artikkel.getKommentaarideArv() + "\t" +
                        artikkel.getJagamisteArv());
            }
        }
    }

    public void sisse(List<Ajaleht> ajalehed, List<String> keelatud) throws IOException {
        System.out.println("Alustasin kaapimist...");
        for (Ajaleht ajaleht : ajalehed) {
            ajaleht.getArtiklid();
            System.out.println(ajaleht.getNimi() + " done!");
        }
    }

}
