package com.company;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final static String[] menus = {"Задание 1", "Задание 2", "Задание 3","Задание 4","Задание 5","Выход"};

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();

        Task[] tasks = {task1, task2};
        Scanner in = new Scanner(System.in);
        while (true){
            Menu();
            int num = in.nextInt();
            if (num - 1 < menus.length - 1 && num - 1 >= 0){
                tasks[num-1].Menu();
            }
            else if (num == menus.length){
                break;
            }
            else {
                System.out.printf("Вводите только цифры от 1 до %d\n", menus.length);
                WaitForEnter();
            }
        }


    }

    public static void WaitForEnter() {
        Console c = System.console();
        if (c != null) {
            // printf-like arguments
            c.format("\nPress ENTER to proceed.\n");
            c.readLine();
        }
        else {
            System.out.println("Press Enter to continue");
            try{System.in.read();}
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void Menu(){
        for (int i = 0; i < menus.length; i++){
            System.out.printf("%d) %s\n", i+1,menus[i]);
        }
    }
}
