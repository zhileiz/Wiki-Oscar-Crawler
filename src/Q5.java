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
public class Q5 extends Solution{
    Document doc;
    int sum;
    HashMap<String, Integer> directors;

    public Q5(String url, int question, int sum) throws IOException {
        super(url, question);
        doc = Jsoup.connect(url).get();
        this.sum = sum;
        this.directors = new HashMap<String, Integer>();
    }

    @Override
    public String solve(){
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

        return printHashMap(directors);
    }

    private String printHashMap(HashMap<String, Integer> hash){
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String,Integer>> it = hash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,Integer> pair = it.next();
            if (pair.getValue() > sum) {
                sb.append(pair.getKey() + ": " + pair.getValue() + "\n");
            }
        }
        return sb.toString();
    }
}
