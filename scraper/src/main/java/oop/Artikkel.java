package oop;

public class Artikkel {

    private String pealkiri;
    private String link;
    private String aeg;
    private String tekst;
    private String portaal;

    public Artikkel(String pealkiri, String link, String aeg, String tekst, String portaal) {
        this.pealkiri = pealkiri;
        this.link = link;
        this.aeg = aeg;
        this.tekst = tekst;
        this.portaal = portaal;
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

}
