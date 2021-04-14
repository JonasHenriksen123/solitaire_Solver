import control.Controller;
import picrecaccess.CardPlacementDAO;
import picrecaccess.ICardPlacementDAO;
import picrecaccess.TestCardPlacementDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("solitaire solver webservices is starting");

        System.out.println("Access live feed or test feeder? (livefeed = l / testfeed = t)");
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            if (input.equals("l") || input.equals("t")) {
                break;
            }
            System.out.println("Input should be \"l\" or \"t\"");
        } while (true);

        //start timer for when initialize started
        long startTime = System.currentTimeMillis();

        ICardPlacementDAO cardPlacementDAO;
        if (input.equals("t")) {
            cardPlacementDAO = new TestCardPlacementDAO();
        } else {
            cardPlacementDAO = new CardPlacementDAO();
        }

        Controller controller = new Controller(cardPlacementDAO);

        //TODO start up web service for solitaire solver

        //TODO start up webapp service

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("System was interrupted");
        }



        System.out.println("System started in \"" + (System.currentTimeMillis() - startTime) + "\" ms");
    }
}
