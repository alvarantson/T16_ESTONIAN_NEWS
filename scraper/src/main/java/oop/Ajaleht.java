package oop;

import java.io.IOException;
import java.util.List;

public abstract class Ajaleht {

    private String url;
    private String nimi;

    public Ajaleht(String url, String nimi) {
        this.url = url;
        this.nimi = nimi;
    }

    public String getUrl() {
        return url;
    }

    public String getNimi() {
        return nimi;
    }

    public abstract List<Artikkel> getArtiklid() throws IOException;

}
