package cinema;

import java.util.Scanner;

public class Cinema {
    private static int purchasedTickets = 0;
    private static int currentIncome = 0;
    public static String[][] cinema;
    private static final Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int seats;
    public static void buyTicket() {
        System.out.println();
        boolean checkInput = true;
        int res = 0;
        int x = 0;
        int y = 0;

        while (checkInput) {
            System.out.println("Enter a row number:");
            x = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            y = scanner.nextInt();
            if(x <= rows && y <= seats) {
                checkInput = false;
                if(rows * seats <= 60)
                    res = 10;
                else if (x <= rows/2)
                    res = 10;
                else
                    res = 8;
            } else {
                System.out.println("Wrong input!");
            }
        }

        if(" B".equals(cinema[x][y])){
            System.out.println("\nThat ticket has already been purchased!\n");
            buyTicket();
        } else {
            System.out.println();
            System.out.println("Ticket price: $" + res);
            System.out.println();

            cinema[x][y] = " B";

            purchasedTickets++;

            currentIncome += res;
        }
    }

    public static void printHole(){
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
            for(int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int showMenu(){
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        return scanner.nextInt();
    }

    public static void fillCinema() {
        cinema = new String[rows + 1][seats + 1];
        for (int i = 0; i <= rows; i++) {
            if (i == 0) {
                for (int j = 0; j <= seats; j++) {
                    if(j == 0) {
                        cinema[0][0] = " ";
                    } else
                        cinema[i][j] = " " + j;
                }
            } else {
                cinema[i][0] = String.valueOf(i);
                for(int j = 1; j <= seats; j++){
                    cinema[i][j] = " S";
                }
            }
        }
    }

    public static void statistics() {
        int totalIncome;
        double percentage;

        if(rows * seats <= 60)
            totalIncome = rows * seats * 10;
        else {
            totalIncome = (rows / 2 * seats * 10) + ((rows / 2 + rows % 2) * seats * 8);
        }

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        try {
            percentage = 1.0 * purchasedTickets / (rows * seats) * 100;
            System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        fillCinema();

        int pick = showMenu();
        while (pick != 0) {
            switch (pick){
                case 1:
                    printHole();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    break;
            }
            pick = showMenu();
        }
    }
}