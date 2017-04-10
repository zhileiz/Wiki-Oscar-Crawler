import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Q2 extends Solution{
    Document doc;
    String screenplay;

    public Q2(String url, int question, String play) throws IOException{
        super(url, question);
        doc = Jsoup.connect(url).get();
        screenplay = play;
    }

    public String solve(){
        Elements table = doc.select("table.wikitable");
        for (Element tb : table){
            for (Element tr : tb.select("tr")){
                Elements tds = tr.select("td");
                for (int i = 0; i < tds.size(); i++){
                    if (tds.get(i).text().contains(screenplay)){
                        if (tds.get(i-1).text().contains("!")){
                            return tds.select("span.sorttext").text();
                        } else {
                            return tds.get(i - 1).text();
                        }
                    }
                }
            }
        }
        return "Play not exist, please check spelling!";
    }

    @Override
    String getSolution() {
        solve();
        return "Answer to Q" + this.question + ": " + solve() + "\n";
    }
}
