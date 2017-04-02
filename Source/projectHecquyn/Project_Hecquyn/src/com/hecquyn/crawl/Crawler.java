package com.hecquyn.crawl;

import com.hecquyn.database.Data;
import java.util.ArrayList;


/**
 *
 * @author Vuong Gia Phu
 */
public abstract class Crawler {
    protected String url;
    
    public Crawler(String link) {
        url = link;
    }
    
    public abstract ArrayList<Data> processPage();
}
