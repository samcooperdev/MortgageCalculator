package com.alphacentauri;

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
    // Greet User
        greetUser("Sam", "Cooper");

    //  Principal
        int principal = (int) readNumber("Principal: ", 1000,1_000_000);

    //  Interest Rates
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);

    //  Period of years
        byte years = (byte) readNumber("Period (Years): ", 1, 30);

    // Print the outputs
        printMortgage(principal, annualInterest, years);
        printPaymentSchedule(principal, annualInterest, years);
    }

    public static void greetUser(String firstName, String lastName) {

        System.out.println("Hello " + firstName + " " + lastName);
        System.out.println("Welcome to the Mortgage Calculator" +
                "\nPlease provide the following details" +
                "\n------------------------------------");
    }

    private static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner sc = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = sc.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between" + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years) {

        // Calculate mortgage value
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }
    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        return principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments)
                - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }
}
