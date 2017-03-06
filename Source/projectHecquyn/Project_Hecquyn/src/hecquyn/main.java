/*
main test
 */
package hecquyn;

import java.io.IOException;

/**
 *
 * @author Vuong Gia Phu
 */
public class main {
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://vnexpress.net/");
        crawler.processPage();
    }
}
