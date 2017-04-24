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

    @Override
    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        
        
        //Data
        String link = "";
        String title = "";
        String date = "";
        String image = "";
        ArrayList<String> text = new ArrayList<String>();
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
                            //String text = e1.toString();
                            //Data data = new Data(link, text);
                            //list.add(data);
                            if(e1.hasClass("the-article-title cms-title")) {
                                System.out.println(e1.text());
                                System.out.println("--");
                            }
                        }
                        
                        //get title
                        Elements elements2 = doc1.getElementsByClass("the-article-tags");
                        for (Element _elements2 : elements2) {
                            //get title
                            //System.out.println(link);
                            title = _elements2.text();
                            //System.out.println("---");
                            
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
                        
                        //get date
                        Elements elements3 = doc1.getElementsByClass("the-article-meta");
                        for(Element _elements3 : elements3) {
                            date = _elements3.text();
                        }
                        
                        //get image
                        Elements elements4 = doc1.getElementsByClass("pic");
                        for(Element _elements4 : elements4) {
                            Elements e4 = _elements4.children();
                            for(Element _e4 : e4) {
                                image = _e4.attr("src");
                            }
                        }
                        
                        //get tag
                        Elements elements5 = doc1.getElementsByClass("the-article-tags");
                        for(Element _elements5 : elements5) {
                            Elements e5 = _elements5.children();
                            for(Element _e5 : e5) {
                                tag.add(_e5.tagName("span").text());
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
