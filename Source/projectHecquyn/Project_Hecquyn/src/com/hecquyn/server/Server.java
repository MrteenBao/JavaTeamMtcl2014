/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecquyn.server;

import com.hecquyn.crawl.Bongda;
import com.hecquyn.crawl.Vnexpress;
import com.hecquyn.crawl.Zingnews;
import com.hecquyn.database.Data;
import com.hecquyn.database.MyDatabase;
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
    }
    
    
    public static void vnexpress() throws IOException {
        ArrayList<Data> list = new ArrayList<Data>();
        Vnexpress vnexpress = new Vnexpress();
        list = vnexpress.processPage();
        for(int i = 0; i < list.size(); i++) {
            db.insert("http://vnexpress.net/", list.get(i));
        }
    }
    
    public static void zingnews() throws IOException {
        ArrayList<Data> list = new ArrayList<Data>();
        Zingnews zingnews = new Zingnews();
        list = zingnews.processPage();
        for(int i = 0; i < list.size(); i++) {
            db.insert("http://news.zing.vn/", list.get(i));
        }
    }
    
    public static void bongdavn() throws IOException {
        ArrayList<Data> list = new ArrayList<Data>();
        Bongda bongda = new Bongda();
        list = bongda.processPage();
        for(int i = 0; i < list.size(); i++) {
            db.insert("http://bongda.vn/", list.get(i));
        }
    }
    
}
