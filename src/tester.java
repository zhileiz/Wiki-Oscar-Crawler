import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhileiz on 4/9/17.
 */
public class tester {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Academy_Award_for_Best_Picture").get();
        HashMap<Integer, String> boxes = new HashMap<Integer,String>();

        Elements tables = doc.select("table.wikitable");
        for (Element table : tables){
            try {
                Element a = table.select("tr").select("a[href]").first();
                System.out.println(a.text() + ": ");
                String k = getBoxOffice(a.attr("abs:href"));
                int kv = getValue(k);
                System.out.println("Box Office: " + k);
                System.out.println("Round to: " + kv);
                boxes.put(kv, a.text());
            } catch (Exception e){

            }
        }
        printTop(boxes);
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

    private static void printTop(HashMap<Integer, String> hs){
        Iterator<Map.Entry<Integer, String>> it = hs.entrySet().iterator();
        int max = 0;
        while (it.hasNext()){
            Map.Entry<Integer,String> pair = it.next();
            max = Math.max(max,pair.getKey());
        }
        System.out.println("##### Top Box is: " + hs.get(max));
    }


}

