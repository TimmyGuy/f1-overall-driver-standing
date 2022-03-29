import Driver.Driver;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class F1OverallDriverStanding {
    static String apiHost = "http://ergast.com/api/f1/";
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws UnirestException, IOException {

        int choice = mainMenu();
        while (true) {
            switch (choice) {
                case 1:
                    driverInformation();
                    break;
                case 2:
                    // Get all races

            }
        }

    }

    private static void driverInformation() throws UnirestException, IOException {
        System.out.println("Typ het driverId in");

        String driverId = scanner.next();
        Driver driver = Driver.getDriver(apiHost, driverId);
    }

    static int mainMenu() {

        // Selection menu
        System.out.println("Selecteer een optie:");
        System.out.println("1. Coureur informatie");
        System.out.println("2. Alle races");
        System.out.println("3. Vergelijk coureurs");
        System.out.println("4. Vergelijk races");
        System.out.print("Keuze: ");

        return scanner.nextInt();
    }
}
