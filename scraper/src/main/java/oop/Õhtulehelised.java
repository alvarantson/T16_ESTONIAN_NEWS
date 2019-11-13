package oop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Õhtulehelised extends Ajaleht {

    public Õhtulehelised(String url, String nimi) {
        super(url, nimi);
    }

    public List<Artikkel> getArtiklid() throws IOException {
        Document document = Jsoup.connect(super.getUrl()).get();
        List<Artikkel> tagastus = new ArrayList<>();
        String pealkiri;
        String link;
        String aeg;
        String artikliSisu;
        Elements text = document.select(".article-unit--title");
        for (Element item : text) {
            try {
                pealkiri = item.select("a").text();
                link = item.select("a").attr("href");
                if (link.contains("http")) {
                    continue;
                } else {
                    link = super.getUrl() + link;
                }
                Document artikliDok = Jsoup.connect(link).get();

                try {
                    aeg = artikliDok.select(".details--inner").text();
                } catch (Exception e) {
                    aeg = "-";
                }
                artikliSisu = "";
                artikliSisu = artikliSisu + artikliDok.select(".article-main--gallery-title").select(".article-main--content").first().text() + "\n";
                for (Element tekst : artikliDok.select(".page-layout--inner").select("p")) {
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
