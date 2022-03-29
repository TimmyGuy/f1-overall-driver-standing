import driver.Driver;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Scanner;

public class F1OverallDriverStanding {
    static final String apiHost = "https://ergast.com/api/f1/";
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws UnirestException {

        int choice = mainMenu();
        while (true) {
            switch (choice) {
                case 1 -> driverInformation();
                case 2 -> raceInformation();
                case 5 -> System.exit(0);
            }
        }

    }

    private static void raceInformation() {
        System.out.println();
    }

    private static void driverInformation() throws UnirestException {
        System.out.println("Typ het driverId in");

        String driverId = scanner.next();
        Driver driver = Driver.getDriver(apiHost, driverId);
        System.out.println(driver);
    }

    static int mainMenu() {

        // Selection menu
        System.out.println("Selecteer een optie:");
        System.out.println("1. Coureur informatie");
        System.out.println("2. Alle races");
        System.out.println("3. Vergelijk coureurs");
        System.out.println("4. Vergelijk races");
        System.out.println("5. Sluit het programma af");
        System.out.print("Keuze: ");

        return scanner.nextInt();
    }
}
