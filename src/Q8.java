import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Q8 extends Solution{
    String subUrl;
    HashSet<String> list1;
    HashSet<String> list2;
    Document doc;
    Document doc2;

    public Q8(String url, int question, String subUrl) throws IOException {
        super(url, question);
        this.subUrl = subUrl;
        list1 = new HashSet<String>();
        list2 = new HashSet<String>();
        doc = Jsoup.connect(url).get();
        doc2 = Jsoup.connect(subUrl).get();
    }

    public String solve(){
        StringBuilder sb = new StringBuilder();
        Elements prize1 = doc.select("table.sortable").first().select("tr");
        Elements winners1 = new Elements();
        for (Element a : prize1) {
            if (a.select("td").size()>0 && a.select("th").size() > 0) {
                Element person = a.select("td").first().select("a").first();
                winners1.add(person);
                list1.add(person.text());
            }
        }
        Elements prize2 = doc2.select("table.sortable").first().select("tr");
        Elements winners2 = new Elements();
        for (Element a : prize2) {
            if (a.select("td").size()>0 && a.select("th").size() > 0) {
                Element person = a.select("td").first().select("a").first();
                winners2.add(person);
                list2.add(person.text());
            }
        }
        Iterator<String> it = list1.iterator();
        while (it.hasNext()){
            String t = it.next();
            if (list2.contains(t)){
                sb.append(t + "\n");
                System.out.println("##### Both: " + t);
            }
        }
        return sb.toString();
    }

    @Override
    String getSolution() {
        return "Q8 Answer:" + solve();
    }
}
