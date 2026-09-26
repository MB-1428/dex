package com.example.dex;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("      DEX JAVA PROGRAM");
        System.out.println("================================");

        System.out.println("Hello from classes.dex");

        if (args.length > 0) {
            System.out.println("Arguments:");

            for (String arg : args) {
                System.out.println("  " + arg);
            }
        }

        System.out.println("================================");
    }
}