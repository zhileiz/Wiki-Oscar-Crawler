import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhileiz on 4/9/17.
 */
public class tester {


    public static void main(String[] args) throws IOException {


        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Academy_Award_for_Best_Director").get();
        HashMap<String, Integer> directors = new HashMap<String, Integer>();

        Elements tbs = doc.select("table");
        for (Element tb : tbs) {
            Elements trs = tb.select("tr");
            for (Element tr : trs) {
                if (tr.select("td").size() > 1) {
                    Element td = tr.select("td").get(0);
                    if (td.select("a").size() > 0) {
                        String director = td.select("a").get(0).text();
                        if (directors.containsKey(director)) {
                            directors.put(director, directors.remove(director) + 1);
                        } else {
                            directors.put(director, 1);
                        }
                    }
                }
            }
        }

        System.out.println(printHashMap(directors));
    }


    public static String printHashMap(HashMap<String, Integer> hash){
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String,Integer>> it = hash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,Integer> pair = it.next();
            if (pair.getValue() > 3) {
                sb.append(pair.getKey() + ": " + pair.getValue() + "\n");
            }
        }
        return sb.toString();
    }
}

