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

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Academy_Award_for_Best_Actor").get();

        for (Element table : doc.select("table.sortable")) {
            for (Element row : table.select("tr")) {
                if (row.select("td").size() < 3){
                    continue;
                } else {
                    Element td = row.select("td").get(2);
                    if (td.text().contains("!")) {
                        System.out.println(td.select("span.sorttext").text());
                        if (td.select("span.sorttext").text().contains("King")) {
                            System.out.println("!!!KING!!!:" + row.select("td").first().select("a").text()
                                    + " " + td.select("span.sorttext").text());
                        }
                    }
                    else {
                        System.out.println(td.text());
                    }
                }
            }
        }

    }
}
