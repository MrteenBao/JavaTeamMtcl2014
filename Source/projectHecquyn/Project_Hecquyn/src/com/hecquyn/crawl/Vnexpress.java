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
public class Vnexpress extends Crawler {

    public Vnexpress(String link) {
        super(link);
    }

    @Override
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        String txt = "";
        ArrayList<String> text = new ArrayList<String>();
        ArrayList<String> tag = new ArrayList<String>();
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
                            txt = txt + e1.attr("class", "Normal").toString() + "\n";
                        }
                        text.add(txt);
                        txt = "";
                        
                        Elements elements2 = doc1.getElementsByTag("div");
                        for (Element _elements2 : elements2) {
                            if (_elements2.hasClass("block_tag width_common space_bottom_20")) {
                                Elements e2 = _elements2.getElementsByTag("a");
                                for(Element _e2 : e2) {
                                    Element e3 = _e2.attr("class", "tag_item");
                                    String str = e3.attr("title").toString();
                                    tag.add(str);
                                    System.out.println(str);
                                    System.out.println("--");
                                }
                                System.out.println("---------------");
                                for(int i = 0; i<tag.size(); i++) {
                                    System.out.println(tag.get(i));
                                }
                                System.out.println("=======================");
                                int temp = 0;
                                Data data = new Data(link, text.get(temp), tag);
                                list.add(data);
                                System.out.println(tag.size());
                                for(int i = 0; i < tag.size(); i++) {
                                    tag.remove(i);
                                }
                            }
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
