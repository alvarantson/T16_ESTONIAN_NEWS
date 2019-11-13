package oop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFetcher {

    public static void main(String[] args) throws Exception {
        //System.out.println(Fetcher.getKroonika());
        //System.out.println(Fetcher.getKroonika().size());
        Map<String, Integer> globalList = new HashMap<>();
        System.out.println("Alustasin kraapimist...");

        for (Fetcher article : Fetcher.getPostimees()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()1234567890:<>|]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Postimees done!");


        for (Fetcher article : Fetcher.getOhtuleht()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()1234567890:<>|]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Õhtuleht done!");


        for (Fetcher article : Fetcher.getTelegram()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()1234567890:<>|]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Telegram done!");


        for (Fetcher article : Fetcher.getElu24()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()123456<>7890:|]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Elu24 done!");


        for (Fetcher article : Fetcher.getDelfi()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()1234<>567890:|]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Delfi done!");


        for (Fetcher article : Fetcher.getAnneJaStiil()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()12345<>67890|:]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Anne ja Stiil done!");


        for (Fetcher article : Fetcher.getKroonika()) {
            String pealkiri = article.getPealkiri();
            for (String sõna :pealkiri.split(" ")) {
                String uusSõna = sõna.replaceAll("[,.!?()12345<>67890:|]", "").toLowerCase();
                if (uusSõna.equals("") || uusSõna.equals("-")) continue;
                if (globalList.containsKey(uusSõna)) {
                    globalList.put(uusSõna, globalList.get(uusSõna) + 1);
                } else {
                    globalList.put(uusSõna, 1);
                }
            }
        }
        System.out.println("Kroonika done!");

        System.out.println("Tulemusi kokku: "+  globalList.size());


        for (String sõna : globalList.keySet()) {
            System.out.println(globalList.get(sõna) + "\t" + sõna);
        }
    }
}*/
