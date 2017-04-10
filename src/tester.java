import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhileiz on 4/9/17.
 */
public class tester {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Portal:Academy_Award").get();
        Element div = doc.select("table").get(1);
        Elements hrefs = div.select("a[href]");
        for (Element h : hrefs) {
            System.out.println(h.text()+": ");
            System.out.println(h.attr("abs:href"));
        }

    }




}
