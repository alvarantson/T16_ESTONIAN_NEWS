package oop.ajaleht_logic;

import oop.kaapimine.ArtiklidFaili;
import oop.objects.Ajaleht;
import oop.objects.Artikkel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Postimehelised extends Ajaleht {

    private List<String> paramUrlid;

    public Postimehelised(String url, String nimi) {
        super(url, nimi);
        this.paramUrlid = genereeriKuuKaupaParamUrl(24);
    }

    public List<Artikkel> getArtiklid() throws IOException {
        List<Artikkel> tagastus = new ArrayList<>();
        String pealkiri;
        String aeg;
        String artikliSisu;
        String sektsioon;
        String autor;
        int kommentaarideArv;
        int jagamisteArv;
        for (String paramUrl : getParamUrlid()) {
            org.jsoup.nodes.Document document = Jsoup.connect(paramUrl).get();
            String url = super.getUrl();
            while(true) {
                tagastus = new ArrayList<>();
                Elements searchResults = document.select(".search-results__item");
                for (Element result: searchResults) {

                    sektsioon = result.select(".search-result__section-label").select("a").text();
                    autor = result.select(".search-result__authors").text();

                    String artikliLink = result.select(".search-result__headline").select("a").attr("href");
                    Document artikliDok;
                    while(true) {
                        try {
                            artikliDok = Jsoup.connect(artikliLink).get();
                            break;
                        } catch (SocketTimeoutException e) {
                            continue;
                        }
                    }

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
                ArtiklidFaili.kirjuta(tagastus, null);
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
                while(true) {
                    try {
                        document = Jsoup.connect(jargLK).get();
                        break;
                    } catch (SocketTimeoutException e) {
                        continue;
                    }
                }
            }
        }
        return tagastus;
    }


    public List<String> genereeriKuuKaupaParamUrl(int korda) {
        String baas = "https://www.postimees.ee/search?sections=81&fields=body%2Cauthors%2Cheadline&page=0&";
        List<String> tulemus = new ArrayList<>();
        LocalDate lopp = LocalDate.now();
        lopp = lopp.minusDays(1);
        LocalDate algus = lopp.minusMonths(1);
        lopp = lopp.minusDays(1);
        for(int i = 0; i < korda; i++) {
            String start = algus.toString();
            String end = lopp.toString();
            tulemus.add(baas + "start=" + start + "&end=" + end);
            lopp = lopp.minusMonths(1);
            algus = algus.minusMonths(1);
        }
        System.out.println(tulemus);
        return tulemus;
    }

    public List<String> genereeri1PaevaKaupaParamUrl(int paeva) {
        String baas = "https://www.postimees.ee/search?sections=81&fields=body%2Cauthors%2Cheadline&page=0&";
        List<String> tulemus = new ArrayList<>();
        LocalDate lopp = LocalDate.now();
        for(int i = 0; i < paeva; i++) {
            String start = lopp.toString();
            String end = lopp.toString();
            tulemus.add(baas + "start=" + start + "T00%3A00%3A00%2B03%3A00" + "&end=" + end + "T23%3A59%3A59%2B03%3A00");
            lopp = lopp.minusDays(1);
        }
        System.out.println(tulemus);
        return tulemus;
    }

    public List<String> getParamUrlid() {
        return paramUrlid;
    }
}
