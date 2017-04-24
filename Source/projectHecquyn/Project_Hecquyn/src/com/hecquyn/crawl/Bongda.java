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
public class Bongda extends Crawler {

    public Bongda(String link) {
        super(link);
    }

    public void output(String link, String title,
            String date, String image, ArrayList<String> tag) {

        System.out.println("---");
        System.out.println(link);
        System.out.println(title);
        System.out.println(date);
        System.out.println(image);

        System.out.println(tag.size());
        for (int i = 0; i < tag.size(); i++) {
            System.out.println(tag.get(i));
        }
    }
    public String img = "";

    public boolean checkImg() {
        if (img == "") {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();

        //Data
        String link = "";
        String title = "";
        String date = "";
        String image = "";
        String text = "";
        ArrayList<String> tag = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("http://bongda.vn/").get();
            Elements elements = doc.getElementsByTag("h3");
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    //get link, title
                    //System.out.println("---");
                    if (true) {
                        link = "http://bongda.vn/" + _a.attr("href").toString();
                        title = _a.attr("title").toString();
                        //System.out.println(link);
                        //System.out.println(title);

                        Document doc1 = Jsoup.connect(link).get();
                        Elements elements1 = doc1.getElementsByTag("p");
                        for (Element e1 : elements1) {
                            text = text + e1.toString() + "\n";
                        }
                        text = "";

                        //get date
                        Elements elements2 = doc1.getElementsByTag("div");
                        for (Element _elements2 : elements2) {
                            //get date
                            if (_elements2.hasClass("fl")) {
                                Elements e2 = _elements2.children();
                                if (e2.hasClass("time_index")) {
                                    date = e2.text();
                                    //System.out.println(date);
                                }
                            }

                            //get tag
                            if (_elements2.hasClass("tag_detail")) {
                                Elements e2 = _elements2.children();
                                if (e2.hasAttr("href")) {
                                    tag.add(e2.text());
                                }
                            }
                        }

                        //get image
                        Elements elements3 = doc1.getElementsByTag("img");
                        for (Element _elements3 : elements3) {
                            if (_elements3.hasAttr("src") || _elements3.hasAttr("alt src")) {
                                if (_elements3.attr("src").startsWith("http")) {
                                    if (image == "") {
                                        image = _elements3.attr("src");
                                    }
                                }
                            }
                        }

                        Data data = new Data(link, title, date, image, tag);
                        list.add(data);
                        output(link, title, date, image, tag);
                        tag = new ArrayList<String>();

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR Crawler processPage");
        }
        return list;
    }
}
