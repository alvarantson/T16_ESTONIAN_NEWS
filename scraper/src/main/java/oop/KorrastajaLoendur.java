package oop;

import java.io.IOException;
import java.util.*;


public class KorrastajaLoendur {

    private Map<String, Integer> globalList;

    public Map<String, Integer> getGlobalList() {
        return globalList;
    }

    public KorrastajaLoendur() {
        this.globalList = new HashMap<>();
    }

    public void teeKordaLisa(List<Artikkel> artiklid, List<String> keelatud) {
        for (Artikkel artikkel : artiklid) {
            String pealkiri = artikkel.getPealkiri();
            for (String sõna : pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()1234567890:<>|«»]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-") || keelatud.contains(uusSõna)) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
    }

    public void loeSisse(List<Ajaleht> ajalehed, List<String> keelatud) throws IOException {
        System.out.println("Alustasin kaapimist...");
        for (Ajaleht ajaleht : ajalehed) {
            teeKordaLisa(ajaleht.getArtiklid(), keelatud);
            System.out.println(ajaleht.getNimi() + " done!");
        }
    }

}
