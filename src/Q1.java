import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Q1 extends Solution{
    Document doc;
    HashMap<String, Integer> directors;


    public Q1(String url, int question) throws IOException {
        super(url, question);
        doc = Jsoup.connect(url).get();
        directors = new HashMap<String, Integer>();
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    @Override
    public String solve(){
        Elements table = doc.select("table.wikitable");
        HashMap<Integer,String> nominations = new HashMap<Integer, String>();
        for (Element tb : table){
            if (tb.text().startsWith("Studio")) {
                Elements trs = tb.select("tr");
                for (Element tr: trs) {
                    if (tr.select("td").size() > 2){
                        Elements tds = tr.select("td");
                        if (isInteger(tds.get(2).text())) {
                            nominations.put(Integer.parseInt(tds.get(2).text()), tds.get(0).text());
                        }
                    }
                }
            }
        }
        Set<Integer> keys = nominations.keySet();
        int max = 0;
        for (Integer i : keys){
            if (i > max){
                max = i;
            }
        }
        return "The most nominated is " + nominations.get(max) + " with " + max + " nominations.";
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
