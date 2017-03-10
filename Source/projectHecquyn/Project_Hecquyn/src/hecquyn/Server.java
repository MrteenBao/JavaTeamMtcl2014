/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hecquyn;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Vuong Gia Phu
 */
public class Server {
    public static MyDatabase db = new MyDatabase();
    
    public static void main(String[] args) throws IOException {
        String url_vnexpress = "http://vnexpress.net/";
        Crawler crawler = new Crawler(url_vnexpress);
        ArrayList<Data> list = new ArrayList<Data>();
        list = crawler.processPage();
        for(int i = 0; i < list.size(); i++) {
            db.insert(url_vnexpress, list.get(i).getLink(), list.get(i).getText());
        }
    } 
}
