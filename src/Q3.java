import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Q3 extends Solution{
    int age;
    Document doc;
    public Q3(String url, int question, int age) throws IOException {
        super(url, question);
        this.age = age;
        this.doc = Jsoup.connect(url).get();
    }

    public String solve(){
        Elements tables = doc.select("table");
        StringBuilder sb = new StringBuilder();
        String starter = "";
        switch (age) {
            case 1:
                starter = "196";
                break;
            case 2:
                starter = "197";
                break;
            case 3:
                starter = "198";
                break;
            case 4:
                starter = "199";
                break;
            case 5:
                starter = "200";
                break;
            case 6:
                starter = "201";
                break;
            default:
                starter = "220";
                break;
        }
        for (Element tb : tables){
            Elements rows = tb.select("tr");
            for (int r = 0; r < rows.size(); r++){
                if (rows.get(r).text().startsWith(starter)) {
                    sb.append(rows.get(r+1).select("td").get(0).text() + "\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    String getSolution() {
        solve();
        return "Answer to Q" + this.question + ": " + solve() + "\n";
    }
}
