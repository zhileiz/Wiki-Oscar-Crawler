import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Q4 extends Solution{
    Document doc;
    String role;

    public Q4(String url, int question, String role) throws IOException {
        super(url, question);
        doc = Jsoup.connect(url).get();
        this.role = role;
    }

    public String solve(){
        StringBuilder sb = new StringBuilder();
        for (Element table : doc.select("table.sortable")) {
            for (Element row : table.select("tr")) {
                if (row.select("td").size() < 3){
                    continue;
                } else {
                    Element td = row.select("td").get(2);
                    if (td.text().contains("!")) {
                        if (td.select("span.sorttext").text().contains(role)) {
                            sb.append(row.select("td").first().select("a").text() + "\n");
                        }
                    }
                    else {
                        if (td.text().contains(role)) {
                            sb.append(row.select("td").first().select("a").text() + "\n");
                        }
                    }
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
