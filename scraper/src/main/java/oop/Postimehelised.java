package oop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Postimehelised extends Ajaleht {

    public Postimehelised(String url, String nimi) {
        super(url, nimi);
    }

    public List<Artikkel> getArtiklid() throws IOException {
        org.jsoup.nodes.Document document = Jsoup.connect(super.getUrl()).get();
        List<Artikkel> tagastus = new ArrayList<>();
        String pealkiri;
        String link;
        String aeg;
        String artikliSisu;
        Elements text = document.select(".list-article__url");
        for (org.jsoup.nodes.Element item : text) {
            try {
                pealkiri = item.select(".list-article__headline").text();
                link = item.attr("href");
                Document artikliDok = Jsoup.connect(link).get();
                try {
                    aeg = artikliDok.select(".article__publish-date").first().text();
                } catch (Exception e) {
                    aeg = "-";
                }
                artikliSisu = "";
                for (Element tekst : artikliDok.select(".article-body__item").select("p")) {
                    artikliSisu += tekst.text() + "\n";
                }
                tagastus.add(new Artikkel(pealkiri, link, aeg, artikliSisu, super.getNimi()));
            } catch (Exception e) {
                continue;
            }
        }
        return tagastus;
    }

}
