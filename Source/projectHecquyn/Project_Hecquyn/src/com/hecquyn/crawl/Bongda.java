/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecquyn.crawl;

import com.hecquyn.database.Data;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Vuong Gia Phu
 */
public class Bongda extends Crawler{

    public Bongda(String link) {
        super(link);
    }
    
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        String text = "";
        try {
            Document doc = Jsoup.connect("http://bongda.vn/").get();
            Elements elements = doc.getElementsByTag("h3");
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    link = "http://bongda.vn/" + _a.attr("href").toString();
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
