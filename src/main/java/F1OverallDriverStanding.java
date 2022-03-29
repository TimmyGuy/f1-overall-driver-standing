import commandlineTable.CommandLineTable;
import driver.Driver;
import com.mashape.unirest.http.exceptions.UnirestException;
import race.Race;
import result.Result;

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
                case 3 -> driverCompare();
                case 4 -> raceCompare();
                case 5 -> System.exit(0);
            }
        }

    }

    private static void raceCompare() throws UnirestException {
        System.out.println("Typ het jaartal van de eerste race in");
        int yearOne = scanner.nextInt();
        System.out.println("Typ het nummer van deze race in");
        int roundOne = scanner.nextInt();
        System.out.println("Typ het jaartal van de tweede race in");
        int yearTwo = scanner.nextInt();
        System.out.println("Typ het nummer van deze race in");
        int roundTwo = scanner.nextInt();

        Race race1 = Race.getRace(apiHost, yearOne, roundOne);
        Race race2 = Race.getRace(apiHost, yearTwo, roundTwo);

        if(race1 != null && race2 != null) {

            CommandLineTable table = new CommandLineTable();
            table.setShowVerticalLines(true);
            table.setHeaders("", "", "");
            table.addRow(
                    "Seizoen",
                    String.format("%d", race1.getSeason()),
                    String.format("%d", race2.getSeason())
            );
            table.addRow(
                    "Ronde",
                    String.format("%d", race1.getRound()),
                    String.format("%d", race2.getRound())
            );
            table.addRow(
                    "Circuit",
                    race1.getCircuit().getCircuitName() + " (" + race1.getCircuit().getCountry() + ")",
                    race2.getCircuit().getCircuitName() + " (" + race2.getCircuit().getCountry() + ")"
            );
            table.addRow(
                    "Datum",
                    race1.getDate() + " " + race1.getTime(),
                    race2.getDate() + " " + race2.getTime()
            );
            table.addRow("Resultaten", "", "");
            for (int i = 0; i < race1.getResults().size(); i++) {
                table.addRow(
                        "",
                        race1.getResults().get(i).getPosition() + ". " + race1.getResults().get(i).getDriver().getFamilyName() + ", " + race1.getResults().get(i).getDriver().getGivenName() + " (" + race1.getResults().get(i).getStatus() + ")",
                        race2.getResults().get(i).getPosition() + ". " + race2.getResults().get(i).getDriver().getFamilyName() + ", " + race2.getResults().get(i).getDriver().getGivenName() + " (" + race2.getResults().get(i).getStatus() + ")"
                );
            }
            table.print();
        } else {
            System.out.println("Er kon geen race gevonden worden met deze gegevens");
        }

        main(new String[]{});
    }

    private static void raceInformation() throws UnirestException {
        System.out.println("Typ het jaartal in");
        int year = scanner.nextInt();
        System.out.println("Typ het nummer van de race in");
        int round = scanner.nextInt();

        Race race = Race.getRace(apiHost, year, round);

        if(race != null) {

            CommandLineTable table = new CommandLineTable();
            table.setShowVerticalLines(true);
            table.setHeaders("", "");
            table.addRow("Seizoen", String.format("%d", race.getSeason()));
            table.addRow("Ronde", String.format("%d", race.getRound()));
            table.addRow("Circuit", race.getCircuit().getCircuitName() + " (" + race.getCircuit().getCountry() + ")");
            table.addRow("Datum", race.getDate() + " " + race.getTime());
            table.addRow("Resultaten", "");
            for (Result result : race.getResults()) {
                table.addRow("", result.getPosition() + ". " + result.getDriver().getFamilyName() + ", " + result.getDriver().getGivenName() + " (" + result.getStatus() + ")");
            }
            table.print();
        } else {
            System.out.println("Er kon geen race gevonden worden met deze gegevens");
        }

        main(new String[]{});
    }

    private static void driverCompare() throws UnirestException {
        System.out.println("Typ het driverId van de eerste coureur in");
        String driverIdOne = scanner.next();
        System.out.println("Typ het driverId van de tweede coureur in");
        String driverIdTwo = scanner.next();
        Driver driver1 = Driver.getDriver(apiHost, driverIdOne);
        Driver driver2 = Driver.getDriver(apiHost, driverIdTwo);

        if(driver1 != null && driver2 != null) {
            CommandLineTable table = new CommandLineTable();
            table.setShowVerticalLines(true);
            table.setHeaders("", "", "");
            table.addRow("Naam", driver1.getFamilyName() + ", " + driver1.getGivenName(), driver2.getFamilyName() + ", " + driver2.getGivenName());
            table.addRow("Geboortedatum", driver1.getDateOfBirth(), driver2.getDateOfBirth());
            table.addRow("Nationaliteit", driver1.getNationality(), driver2.getNationality());
            table.addRow("Gereden races", "", "");
            int itt = Math.min(driver1.getDrivenRaces().size(), driver2.getDrivenRaces().size());
            for (int i = 0; i < itt; i++) {
                table.addRow(
                        "",
                        driver1.getDrivenRaces().get(i).getDate() + " " + driver1.getDrivenRaces().get(i).getCircuit().getCircuitName() + " (" + driver1.getDrivenRaces().get(i).getResults().get(0).getPosition() + "e)",
                        driver2.getDrivenRaces().get(i).getDate() + " " + driver2.getDrivenRaces().get(i).getCircuit().getCircuitName() + " (" + driver2.getDrivenRaces().get(i).getResults().get(0).getPosition() + "e)"
                );
            }
            table.print();
        } else {
            System.out.println("Er kon geen driver gevonden worden met deze gegevens");
        }
        main(new String[]{});

    }

    private static void driverInformation() throws UnirestException {
        System.out.println("Typ het driverId in");

        String driverId = scanner.next();
        Driver driver = Driver.getDriver(apiHost, driverId);

        if(driver != null) {
            CommandLineTable table = new CommandLineTable();
            table.setShowVerticalLines(true);
            table.setHeaders("", "");
            table.addRow("Naam", driver.getFamilyName() + ", " + driver.getGivenName());
            table.addRow("Geboortedatum", driver.getDateOfBirth());
            table.addRow("Nationaliteit", driver.getNationality());
            table.addRow("Gereden races", "");
            for(Race race : driver.getDrivenRaces()) {
                table.addRow("", race.getDate() + " " + race.getCircuit().getCircuitName() + " (" + race.getResults().get(0).getPosition() + "e)");
            }
            table.print();
        } else {
            System.out.println("De coureur is niet gevonden");
        }
        main(new String[]{});

    }

    static int mainMenu() {

        // Selection menu
        System.out.println("Selecteer een optie:");
        System.out.println("1. Coureur informatie");
        System.out.println("2. Race informatie");
        System.out.println("3. Vergelijk coureurs");
        System.out.println("4. Vergelijk races");
        System.out.println("5. Sluit het programma af");
        System.out.print("Keuze: ");

        return scanner.nextInt();
    }
}
