package hecquyn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Vuong Gia Phu
 */
public class Crawler {

    private String url;

    public Crawler(String link) {
        url = link;
    }

    public ArrayList<Data> processPage() {
        ArrayList<Data> list = new ArrayList<Data>();
        String link = "";
        String text = "";
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
                            text = text + e1.attr("class", "Normal").toString() + "\n"; 
                        }
                        Data data = new Data(link, text);
                        list.add(data);
                        text = "";
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR Crawler processPage");
        }

        return list;
    }

    public void crawlPage() {

    }

    public void testProcessPage() {
        String url = "http://giaitri.vnexpress.net/tin-tuc/gioi-sao/trong-nuoc/sao-viet-bang-hoang-khi-san-khau-ra-mat-bom-tan-kong-o-sai-gon-chay-lon-3553018.html";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("p");
            String text = "";
            for (Element e : elements) {
                text = text + e.attr("class", "Normal").toString() + "\n";
            }
            System.out.println("" + text);
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
