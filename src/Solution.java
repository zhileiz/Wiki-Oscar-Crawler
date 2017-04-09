/**
 * Created by zhileiz on 4/9/17.
 */
public abstract class Solution {
    String url;
    int question;

    public Solution(String url, int question){
        this.url = url;
        this.question = question;
    }

    abstract String getSolution();

}
