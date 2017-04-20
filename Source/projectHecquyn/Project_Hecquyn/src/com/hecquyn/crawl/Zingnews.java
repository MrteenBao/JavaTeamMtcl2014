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
public class Zingnews extends Crawler {

    public Zingnews(String link) {
        super(link);
    }

    @Override
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        ArrayList<String> tag = new ArrayList<String>();
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
                            //Data data = new Data(link, text);
                            //list.add(data);
                        }
                        
                        Elements elements2 = doc1.getElementsByClass("the-article-tags");
                        for (Element _elements2 : elements2) {
                            /*if (_elements2.hasClass("block_tag width_common space_bottom_20")) {
                                Elements e2 = _elements2.getElementsByTag("a");
                                for (Element _e2 : e2) {
                                    Element e3 = _e2.attr("class", "tag_item");
                                    tag.add(e3.attr("title"));
                                }
                                Data data = new Data(link, text.get(temp), tag);
                                list.add(data);
                            }*/
                            //System.out.println(_elements2.toString());
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
