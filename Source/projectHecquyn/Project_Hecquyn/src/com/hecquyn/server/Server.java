/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecquyn.server;

import com.hecquyn.database.Data;
import com.hecquyn.database.MyDatabase;
import com.hecquyn.crawl.Crawler;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Vuong Gia Phu
 */
public class Server {
    public static MyDatabase db = new MyDatabase();
    
    public static void main(String[] args) throws IOException {
        vnexpress();
        zingnews();
        bongdavn();
        /*ham hen gio -> tick timer*/
    }
    
    
    public static void vnexpress() {
        String url = "http://vnexpress.net/";
        Crawler crawler = new Crawler(url);
        ArrayList<Data> list = new ArrayList<Data>();
        list = crawler.processPage_vnexpress();
        for(int i = 0; i < list.size(); i++) {
            db.insert(url, list.get(i).getLink(), list.get(i).getText());
        }
    }
    
    public static void zingnews() throws IOException {
        String url = "http://news.zing.vn/";
        Crawler crawler = new Crawler(url);
        ArrayList<Data> list = new ArrayList<Data>();
        list = crawler.processPage_zingnews();
        for(int i = 0; i < list.size(); i++) {
            db.insert(url, list.get(i).getLink(), list.get(i).getText());
        }
    }
    
    public static void bongdavn() throws IOException {
        String url = "http://bongda.vn/";
        Crawler crawler = new Crawler(url);
        ArrayList<Data> list = new ArrayList<Data>();
        list = crawler.processPage_bongdavn();
        for(int i = 0; i < list.size(); i++) {
            db.insert(url, list.get(i).getLink(), list.get(i).getText());
        }
    }
}
