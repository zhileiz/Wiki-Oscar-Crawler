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
public class Q6 extends Solution{
    Document doc;
    HashMap<Integer, String> offices;

    public Q6(String url, int question) throws IOException {
        super(url, question);
        doc = Jsoup.connect(url).get();
        this.offices = new HashMap<Integer, String>();
    }

    public String solve(){
        Elements tables = doc.select("table.wikitable");
        for (Element table : tables){
            try {
                Element a = table.select("tr").select("a[href]").first();
                System.out.println(a.text() + ": ");
                String k = getBoxOffice(a.attr("abs:href"));
                int kv = getValue(k);
                System.out.println("Box Office: " + k);
                System.out.println("Round to: " + kv);
                offices.put(kv, a.text());
            } catch (Exception e){

            }
        }
        return printTop(offices);
    }

    private static String getBoxOffice(String link) {
        if (link.contains("%27")){
            String str[] = link.split("%27");
            link = str[0] + "'" + str[1];
        }
        Document doc = null;
        String toReturn = "";
        try {
            doc = Jsoup.connect(link).get();
            Element boxOffice = doc.select("table.infobox").first().select("tr").last();
            return boxOffice.select("td").last().text();
        } catch (IOException e) {
            System.out.println("******Failed URL IS: " + link + " ******");
            // e.printStackTrace();
        }
        return toReturn;
    }

    private static int getValue(String input){
        if (input.contains("[")){
            int key = input.indexOf('[');
            input = input.substring(0,key);
        }
        String str = input.replaceAll("[^\\d.]", "");
        if (str.contains(".")){
            int key = str.indexOf('.');
            if (str.substring(key+1, str.length()).contains(".")){
                String front = str.substring(0,key+1);
                String back = str.substring(key+1,str.length()).replace(".", "");
                str = front.concat(back);
            }
        }
        try {
            double dbl = Double.parseDouble(str) / 1000000;
            if (input.contains("million")) {
                dbl = dbl * 1000000;
            } else if (input.contains("billion")) {
                dbl = dbl * 1000000 * 1000;
            }
            if (dbl+0.5 > 1000000){
                dbl = dbl / 1000000;
            }
            return (int) (dbl+0.5);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return 0;
    }

    private static String printTop(HashMap<Integer, String> hs){
        Iterator<Map.Entry<Integer, String>> it = hs.entrySet().iterator();
        int max = 0;
        while (it.hasNext()){
            Map.Entry<Integer,String> pair = it.next();
            max = Math.max(max,pair.getKey());
        }
        return "##### Top Box is: " + hs.get(max);
    }

    @Override
    String getSolution() {
        return "Answer to Q" + this.question + ": " + solve() + "\n";
    }
}
