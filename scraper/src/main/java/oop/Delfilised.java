package oop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Delfilised extends Ajaleht {

    public Delfilised(String url, String nimi) {
        super(url, nimi);
    }

    public List<Artikkel> getArtiklid() throws IOException {
        Document document = Jsoup.connect(super.getUrl()).get();
        List<Artikkel> tagastus = new ArrayList<>();
        String pealkiri;
        String link;
        String aeg;
        String artikliSisu;
        Elements text = document.select(".headline__title");
        for (org.jsoup.nodes.Element item : text) {
            pealkiri = item.text();
            link = item.select("a").attr("href");
            Document artikkel = Jsoup.connect(link).get();
            aeg = artikkel.select(".article__date").text();
            artikliSisu = "";
            for (Element tekst : artikkel.select(".article__body").select("p")) {
                artikliSisu += tekst.text();
            }
            tagastus.add(new Artikkel(pealkiri, link, aeg, artikliSisu, super.getNimi()));
        }
        return tagastus;
    }

}
