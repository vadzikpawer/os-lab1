package com.company;

import java.io.Console;
import java.util.Scanner;

public class Main {

    private final static String[] menus = {"Информацию о дисках", "Чтение и запись файла", "Работа с JSON","Работа с XML","Работа с ZIP-архивом","Выход"};

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        Task4 task4 = new Task4();
        Task5 task5 = new Task5();

        Task[] tasks = {task1, task2, task3, task4, task5};
        Scanner in = new Scanner(System.in);
        while (true){
            Menu();
            int numt = in.nextInt();
            int num;
            if (numt - 1 < menus.length - 1 && numt - 1 >= 0){
                tasks[numt-1].Menu();
                num = in.nextInt();
                if (num > 0 && num <= tasks[numt-1].menus.length){
                    tasks[numt-1].func.get(num-1).apply(null);
                    WaitForEnter();
                }
                else {
                    System.out.printf("Вводите только цифры от 1 до %d\n", tasks[numt-1].menus.length);
                    WaitForEnter();
                    tasks[numt-1].Menu();
                }
            }
            else if (numt == menus.length){
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
