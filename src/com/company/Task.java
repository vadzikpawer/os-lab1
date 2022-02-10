package com.company;

import java.io.Console;
import java.util.Scanner;

public abstract class Task {
    Scanner in = new Scanner(System.in);
    public abstract void Menu();

    public static void WaitForEnter() {
        Console c = System.console();
        if (c != null) {
            // printf-like arguments
            c.format("\nPress ENTER to proceed.\n");
            c.readLine();
        }
        else {
            System.out.println("\nНажмите Ввод, чтобы продолжить\n");
            try{System.in.read();}
            catch(Exception e){}
        }
    }
}
