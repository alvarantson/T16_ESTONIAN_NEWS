package oop.objects;

import java.io.IOException;
import java.util.List;

public abstract class Ajaleht {

    private String url;
    private String nimi;
    private String paramUrl;

    public Ajaleht(String url, String nimi) {
        this.url = url;
        this.nimi = nimi;
    }

    public Ajaleht(String url, String nimi, String paramUrl) {
        this.url = url;
        this.nimi = nimi;
        this.paramUrl = paramUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getNimi() {
        return nimi;
    }

    public String getParamUrl() {
        return paramUrl;
    }

    public abstract List<Artikkel> getArtiklid() throws IOException;

}
