package hecquyn;

import java.io.IOException;
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

    public void processPage() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("h3");
            for (Element e : elements) {
                System.out.println(e.toString());
                Elements ul = e.children();
                for(Element _e : ul) {
                    if(_e.tagName().trim().equals("a")) {
                        Elements as = _e.children();
                        for(Element _a : as) {
                            System.out.println(_a.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR Crawler processPage");
        }
    }
}
