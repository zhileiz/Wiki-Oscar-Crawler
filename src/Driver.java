import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Driver {
    Scanner src;
    Document doc;
    HashMap<String, Element> links;

    public Driver() throws IOException{
        System.out.println("##### Welcome to Oscar Info #####");
        src = new Scanner(System.in);
        doc = Jsoup.connect("https://en.wikipedia.org/wiki/Portal:Academy_Award").get();
        links = new HashMap<String, Element>();
        getLinks();
    }

    public void loop(){
        while (true){
            System.out.println("What do you want to know?: ");
            printQuestions();
            String input = src.nextLine();
            if (input.equals("quit") || input.equals("exit")){
                break;
            } else {
                try {
                    int qNum = Integer.parseInt(input);
                    System.out.println(returnAnswer(qNum));
                } catch (NumberFormatException e){
                    System.out.println("Not a Number, try again!");
                }
            }
        }
    }

    private void getLinks(){
        Element div = doc.select("table").get(1);
        Elements hrefs = div.select("a[href]");
        for (Element h : hrefs) {
            links.put(h.text(), h);
        }
    }

    public String returnAnswer(int k){
        String answer = "";
        String url = "";
        Solution sl;
        switch (k) {
            case 1:
                url = links.get("Best Animated Feature").attr("abs:href");
                try {
                    sl = new Q1(url, 1);
                    answer = sl.getSolution();
                } catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case 2:
                url = links.get("Best Original Screenplay").attr("abs:href");
                System.out.println("Enter the Screeplay title: ");
                String input = src.nextLine();
                try {
                    sl = new Q2(url, 2, input);
                    answer = sl.getSolution();
                } catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.print("Enter Category: ");
                boolean hasInput = false;
                while (true) {
                    String in = src.nextLine();
                    try {
                        in = correctFormat(in);
                        url = links.get(in).attr("abs:href");
                        if (url == null) {
                            System.out.println("Gibberish Input, try again: ");
                        } else {
                            hasInput = true;
                            break;
                        }
                    } catch (Exception e){
                        System.out.println("Gibberish Input, try again: ");
                        continue;
                    }
                }
                System.out.println("Choose Decade of: ");
                System.out.println("1. 1960s");
                System.out.println("2. 1970s");
                System.out.println("3. 1980s");
                System.out.println("4. 1990s");
                System.out.println("5. 2000s");
                System.out.println("6. 2010s");
                String ip = src.nextLine();
                try {
                    int it = Integer.parseInt(ip);
                    sl = new Q3(url, 3, it);
                    answer = sl.getSolution();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 4:
                url = links.get("Best Leading Actor").attr("abs:href");
                System.out.println("Please enter the role");
                String role = src.nextLine();
                try {
                    sl = new Q4(url, 4, role);
                    answer = sl.getSolution();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 5:
                url = links.get("Best Director").attr("abs:href");
                System.out.println("Enter Number of times: ");
                String time = src.nextLine();
                try {
                    int t = Integer.parseInt(time);
                    sl = new Q5(url, 5,t);
                    answer = sl.getSolution();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 6:
                System.out.print("Enter Category: ");
                boolean isInput = false;
                while (true) {
                    String in = src.nextLine();
                    try {
                        in = correctFormat(in);
                        url = links.get(in).attr("abs:href");
                        if (url == null) {
                            System.out.println("Gibberish Input, try again: ");
                        } else {
                            isInput = true;
                            break;
                        }
                    } catch (Exception e){
                        System.out.println("Gibberish Input, try again: ");
                        continue;
                    }
                }
                try {
                    sl = new Q6(url, 6);
                    answer = sl.getSolution();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 7:
                url = links.get("Best Animated Feature").attr("abs:href");
                sl = new Q7(url, 7);
                answer = sl.getSolution();
                break;
            case 8:
                url = links.get("Best Animated Feature").attr("abs:href");
                sl = new Q8(url, 8);
                answer = sl.getSolution();
                break;
            case 9:
                url = links.get("Best Animated Feature").attr("abs:href");
                sl = new Q9(url, 9);
                answer = sl.getSolution();
                break;
            case 10:
                url = links.get("Best Animated Feature").attr("abs:href");
                sl = new Q10(url, 10);
                answer = sl.getSolution();
                break;
        }
        return answer;
    }

    private void printQuestions(){
        System.out.println("1. Best Animated Feature: Companies with most nomination");
        System.out.println("2. Best Original Screenplay: Who wrote...?");
        System.out.println("3. Best Documentary Feature: 1960s? 1970s? 1990s?");
        System.out.println("4. Best Leading Actor: Role as a ...?");
        System.out.println("5. Best Director: Who got nominated ... times?");
        System.out.println("6. Highest Box Office for film that won ... ?");
        System.out.println("7. People who won ... over the age of ... ?");
        System.out.println("8. (WildCard) People who won ... also won ... ?");
        System.out.println("------ Extra Credit ------");
        System.out.println("9. Movies with all big category nominations? ");
        System.out.println("10.(WildCard) Nominated at least 2 times but no wins in ... ?");
        System.out.println("!! Or type 'quit' or 'exit' to exit !!");
    }

    private String correctFormat(String str){
        str = str.toLowerCase();
        String st[] = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : st){
            sb.append(s.substring(0,1).toUpperCase() + s.substring(1));
            sb.append(" ");
        }
        return sb.toString().substring(0,sb.length()-1);
    }

}
