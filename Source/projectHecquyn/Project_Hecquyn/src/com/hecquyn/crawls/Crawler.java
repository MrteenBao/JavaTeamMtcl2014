package com.hecquyn.crawl;

import com.hecquyn.database.Data;
import java.util.ArrayList;


public abstract class Crawler {
    
    public abstract ArrayList<Data> processPage();

}
