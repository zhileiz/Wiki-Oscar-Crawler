import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * Created by zhileiz on 4/9/17.
 */
public class Driver {
    Scanner src;

    public Driver(){
        System.out.println("##### Welcome to Oscar Info #####");
        src = new Scanner(System.in);
    }

    public void loop(){
        while (true){
            System.out.println("What do you want to know?: ");
            printQuestions();
            String input = src.next();
            if (input.equals("quit") || input.equals("exit")){
                break;
            } else {
                try {
                    int qNum = Integer.parseInt(input);
                    System.out.println(returnAnswer("url", qNum));
                } catch (NumberFormatException e){
                    System.out.println("Not a Number, try again!");
                }
            }
        }
    }

    public String returnAnswer(String url, int k){
        String answer = "";
        Solution sl;
        switch (k) {
            case 1:
                sl = new Q1(url, 1);
                answer = sl.getSolution();
                break;
            case 2:
                sl = new Q2(url, 2);
                answer = sl.getSolution();
                break;
            case 3:
                sl = new Q3(url, 3);
                answer = sl.getSolution();
            case 4:
                sl = new Q4(url, 4);
                answer = sl.getSolution();
                break;
            case 5:
                sl = new Q5(url, 5);
                answer = sl.getSolution();
                break;
            case 6:
                sl = new Q6(url, 6);
                return sl.getSolution();
            case 7:
                sl = new Q7(url, 7);
                answer = sl.getSolution();
                break;
            case 8:
                sl = new Q8(url, 8);
                answer = sl.getSolution();
                break;
            case 9:
                sl = new Q9(url, 9);
                answer = sl.getSolution();
                break;
            case 10:
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

}
