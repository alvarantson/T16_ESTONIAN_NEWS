package oop.objects;

public class Artikkel {

    private String pealkiri;
    private String link;
    private String aeg;
    private String tekst;
    private String portaal;
    private String sektsioon;
    private String autor;
    private int kommentaarideArv;
    private int jagamisteArv;

    public Artikkel(String pealkiri, String link, String aeg, String tekst, String portaal, String sektsioon, String autor, int jagamisteArv, int kommentaarideArv) {
        this.pealkiri = pealkiri;
        this.link = link;
        this.aeg = aeg;
        this.tekst = tekst;
        this.portaal = portaal;
        this.sektsioon = sektsioon;
        this.autor = autor;
        this.jagamisteArv = jagamisteArv;
        this.kommentaarideArv = kommentaarideArv;
    }

    public String getSektsioon() {
        return sektsioon;
    }

    public String getAutor() {
        return autor;
    }

    public String getPealkiri() {
        return pealkiri;
    }

    public String getLink() {
        return link;
    }

    public String getAeg() {
        return aeg;
    }

    public String getTekst() {
        return tekst;
    }

    public String getPortaal() {
        return portaal;
    }

    public int getKommentaarideArv() {return kommentaarideArv;}

    public int getJagamisteArv() {
        return jagamisteArv;
    }
}
