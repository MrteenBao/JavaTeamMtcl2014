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
    
    public void output(String link, String title, 
            ArrayList<String> text, String date, String image, ArrayList<String> tag) {
        
        System.out.println("---");
        System.out.println(link);
        System.out.println(title);
        System.out.println(date);
        System.out.println(image);
        
        System.out.println(tag.size());
        for(int i = 0; i < tag.size(); i++) {
            System.out.println(tag.get(i));
        }
        
        //return null;
    }

    @Override
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        String txt = "";
        int temp = 0;

        //Data
        String link = "";
        String title = "";
        String date = "";
        String image = "";
        ArrayList<String> text = new ArrayList<String>();
        ArrayList<String> tag = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("h3"); // get link
            for (Element e : elements) {
                Elements a = e.children();
                for (Element _a : a) {
                    link = _a.attr("href").toString();
                    if (!link.equals("")) {
                        Document doc1 = Jsoup.connect(link).get();
                        Elements elements1 = doc1.getElementsByTag("p"); // get text
                        for (Element e1 : elements1) {
                            txt = txt + e1.attr("class", "Normal").toString() + "\n";
                        }
                        text.add(txt);
                        txt = "";
                        
                        Elements elements2 = doc1.getElementsByTag("div");
                        for (Element _elements2 : elements2) {
                            //get hashtag
                            if (_elements2.hasClass("block_tag width_common space_bottom_20")) {
                                Elements e2 = _elements2.getElementsByTag("a");
                                for (Element _e2 : e2) {
                                    Element e3 = _e2.attr("class", "tag_item");
                                    String str = e3.attr("title").toString();
                                    tag.add(str);
                                }
                                //Data data = new Data(link, text.get(temp), tag);
                                //list.add(data);
                                //temp++;
                                /*for (int i = 0; i < tag.size() + 1; i++) {
                                    tag.remove(i);
                                }*/
                            }
                            
                            //get title
                            if (_elements2.hasClass("title_news")) {
                                title = _elements2.text();
                                //System.out.println(title);
                            }
                            
                            //get date
                            if (_elements2.hasClass("block_timer_share")) {
                                date = _elements2.text();
                            }
                            
                            //get image
                            if (_elements2.hasClass("fck_detail width_common block_ads_connect")) {
                                Elements e3 = _elements2.getElementsByTag("img");
                                for(Element _e3 : e3) {
                                    image = _e3.attr("src");
                                }
                            }
                        }
                        output(link, title, text, date, image, tag);
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
