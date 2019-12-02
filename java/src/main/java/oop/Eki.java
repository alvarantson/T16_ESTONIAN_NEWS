package oop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Eki {


    public static List<String> findBaseWords(String searchword) throws IOException {
        String url = "https://sonaveeb.ee/search/est-est/detail/" + searchword + "/1?ieuser=false";
        List<String> result = new ArrayList<>();

        Document document = Jsoup.connect(url).get();
        result.add(document.select(".homonym-name").text().split(" ")[0]);

        return result; // kui ei leia midagi, tagastab ""
    }


    public static List<String> findAllWords(String searchword) throws IOException, InterruptedException  {

        String url = "https://sonaveeb.ee/search/est-est/detail/" + searchword + "/1?ieuser=false";
        List<String> result = new ArrayList<>();

        Document document = Jsoup.connect(url).get();

        Elements otherWords = document.select(".pt-1").select(".btn"); // doesnt work
        Elements homonyms = document.select(".homonym-header");

        result.addAll(findWordForms(searchword));

        for (Element otherWord : otherWords) {
            result.addAll(findWordForms(otherWord.text()));
        }

        for (Element homonym : homonyms) {
            result.addAll(findWordForms(homonym.text()));
        }

        return result;

    }

    public static List<String> findWordForms(String baseWord) throws IOException, InterruptedException {

        String url = "https://sonaveeb.ee/search/est-est/detail/" + baseWord + "/1?ieuser=false";
        url = url.replace(" ", "%20");
        List<String> result = new ArrayList<>();

        Document dokument = Jsoup.connect(url).get();
        System.out.println(url);
        Elements elements = dokument.getAllElements(); // doesnt work
        System.out.println(elements);
        for (Element element : elements) {
            result.add(element.text());
        }

        return result;

    }

}
