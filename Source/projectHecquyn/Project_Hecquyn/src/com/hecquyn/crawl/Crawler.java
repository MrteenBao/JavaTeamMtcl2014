package com.hecquyn.crawl;

import com.hecquyn.database.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Vuong Gia Phu
 */
public class Crawler {

    private String url;

    public Crawler(String link) {
        url = link;
    }

    public ArrayList<Data> processPage_vnexpress() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        String text = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("h3");
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    link = _a.attr("href").toString();
                    if (!link.equals("")) {
                        Document doc1 = Jsoup.connect(link).get();
                        Elements elements1 = doc1.getElementsByTag("p");
                        for (Element e1 : elements1) {
                            text = text + e1.attr("class", "Normal").toString() + "\n";
                        }
                        Data data = new Data(link, text);
                        list.add(data);
                        text = "";
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR Crawler processPage");
        }
        return list;
    }

    public ArrayList<Data> processPage_zingnews() throws IOException {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("title");
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    String temp = _a.attr("href").toString();
                    if (!temp.equals("")) {
                        link = url + temp;
                        Document doc1 = Jsoup.connect(link).get();
                        Elements elements1 = doc1.getElementsByClass("the-article-body cms-body");
                        for (Element e1 : elements1) {
                            String text = e1.toString();
                            Data data = new Data(link, text);
                            list.add(data);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR Crawler processPage");
        }
        return list;
    }

    public ArrayList<Data> processPage_bongdavn() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        String text = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("h3");
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    link = url + _a.attr("href").toString();
                    Document doc1 = Jsoup.connect(link).get();
                    Elements elements1 = doc1.getElementsByTag("p");
                    for (Element e1 : elements1) {
                        text = text + e1.toString() + "\n";
                    }
                    Data data = new Data(link, text);
                    list.add(data);
                    text = "";
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR Crawler processPage");
        }
        return list;
    }
}
