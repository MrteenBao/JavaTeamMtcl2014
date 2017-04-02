package com.hecquyn.crawl;

import com.hecquyn.database.Data;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Zingnews extends Crawler {
    
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        try {
            Document doc = Jsoup.connect("http://news.zing.vn/").get();
            Elements elements = doc.getElementsByClass("title");
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    String temp = _a.attr("href").toString();
                    if (!temp.equals("")) {
                        link = "http://news.zing.vn/" + temp;
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
}
