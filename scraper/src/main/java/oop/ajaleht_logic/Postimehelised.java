package oop.ajaleht_logic;

import oop.objects.Ajaleht;
import oop.objects.Artikkel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Postimehelised extends Ajaleht {

    public Postimehelised(String url, String nimi, String paramurl) {
        super(url, nimi, paramurl);
    }

    public List<Artikkel> getArtiklid() throws IOException {
        org.jsoup.nodes.Document document = Jsoup.connect(super.getParamUrl()).get();
        String url = super.getUrl();
        List<Artikkel> tagastus = new ArrayList<>();
        String pealkiri;
        String aeg;
        String artikliSisu;
        String sektsioon;
        String autor;
        int kommentaarideArv;
        int jagamisteArv;
        while(true) {
            Elements searchResults = document.select(".search-results__item");
            for (Element result: searchResults) {

                sektsioon = result.select(".search-result__section-label").select("a").text();
                autor = result.select(".search-result__authors").text();

                String artikliLink = result.select(".search-result__headline").select("a").attr("href");
                Document artikliDok = Jsoup.connect(artikliLink).get();

                pealkiri = artikliDok.select(".article-headline").text();
                if (pealkiri.isBlank()) {
                    continue;
                }
                try {
                    aeg = artikliDok.select(".article__publish-date").first().text();
                } catch (Exception e) {
                    aeg = "-";
                }
                try {
                    kommentaarideArv = Integer.parseInt(artikliDok.select(".article-share__item--comments").select(".article-share__item--count").get(0).text());

                } catch (Exception e) {
                    kommentaarideArv = 0;
                }
                try {
                    jagamisteArv = Integer.parseInt(artikliDok.select(".article-share__item--facebook").select(".article-share__item--count").get(0).text());
                } catch (Exception e) {
                    jagamisteArv = 0;
                }
                artikliSisu = "";
                for (Element tekst : artikliDok.select(".article-body__item").select("p")) {
                    artikliSisu += tekst.text() + " ";
                }
                tagastus.add(new Artikkel(pealkiri, artikliLink, aeg, artikliSisu, super.getNimi(), sektsioon, autor, jagamisteArv, kommentaarideArv));
            }
            Elements järk = document.select(".pagination__link");
            Element element = null;
            for (Element i: järk) {
                if (i.text().equals("JÄRGMINE")) {
                    element = i;
                }
            }
            if (element == null) {
                break;
            }
            String jargLK = url + element.attr("href");
            System.out.println(jargLK);
            document = Jsoup.connect(jargLK).get();
        }
        return tagastus;
    }

}
