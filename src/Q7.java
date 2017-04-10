import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Q7 extends Solution{
    Document doc;
    int limit;

    public Q7(String url, int question, int age) throws IOException {
        super(url,question);
        doc = Jsoup.connect(url).get();
        this.limit = age;
    }

    @Override
    public String solve(){
        System.out.print("Loading...");
        StringBuilder sb = new StringBuilder();
        Elements events = doc.select("table.sortable").first().select("th");
        Elements Oscars = new Elements();
        Elements prizes = doc.select("table.sortable").first().select("tr");
        Elements actors = new Elements();
        for (Element a : prizes) {
            if (a.select("td").size()>0 && a.select("th").size() > 0) {
                actors.add(a.select("td").first().select("a").first());
            }
        }
        for (Element a : events){
            if (a.select("a[href]").size() != 0) {
                Oscars.add(a.select("a").last());
            }
        }
        for (int k = 0; k < Oscars.size(); k++){
            try{
                String os = getOscarDate(Oscars.get(k).attr("abs:href"));
                String ac = getDate(getBirthDate(actors.get(k).attr("abs:href")));
                int ag = computeAge(os,ac);
                if (ag >= limit) {
                    sb.append(Oscars.get(k).text() + ": " + actors.get(k).text() + " was " + ag + " years old.\n");
                    System.out.print("..");
                }
            } catch (Exception e){
            }
        }
        return sb.toString();
    }

    private static String getOscarDate(String link){
        Document doc = null;
        String toReturn = "";
        try {
            doc = Jsoup.connect(link).get();

            Elements info = doc.select("table.infobox").first().select("tr");
            Element dateObj;
            for (int i = 0; i < info.size(); i ++){
                Element tr = info.get(i);
                if (tr.select("th").size() > 0){
                    if (tr.select("th").first().text().equals("Date")){
                        toReturn = tr.select("td").first().text();
                    }
                }
            }
        } catch (IOException e) {
        }
        return toReturn;
    }

    private static String getBirthDate(String link){
        Document doc = null;
        String toReturn = "";
        try {
            doc = Jsoup.connect(link).get();

            Elements info = doc.select("table.infobox").first().select("tr");
            Element dateObj;
            for (int i = 0; i < info.size(); i ++){
                Element tr = info.get(i);
                if (tr.select("th").size() > 0){
                    if (tr.select("th").first().text().equals("Born")){
                        toReturn = tr.select("td").first().text();
                    }
                }
            }
        } catch (IOException e) {
            //System.out.println("******Failed URL IS: " + link + " ******");
            // e.printStackTrace();
        }
        return toReturn;
    }

    private static String getDate(String str){
        if (str.contains("(") && str.contains(")")) {
            int start = str.indexOf('(');
            int end = str.indexOf(')');
            return str.substring(start+1, end);
        } else {
            return "-----Birthday Cannot Be Parsed-----\n";
        }
    }

    private static int computeAge(String oscar, String birth){
        int year = Integer.parseInt(oscar.substring(oscar.length()-4));
        int birthyear = Integer.parseInt(birth.substring(0,4));
        return year-birthyear;
    }

}
