package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Task2 extends Task {

    private final String[] menus = {"Создать файл", "Записать в файл", "Вывести информацию из файла", "Удалить файл"};
    private final List<Function<Void, Boolean>> func = Arrays.asList((num)->CreateFile(),(num)->WriteToFile(),(num)->ReadFromFile(),(num)->DeleteFile());

    @Override
    public void Menu(){
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
        int num = in.nextInt();
        if (num - 1 >= 0 && num < menus.length){
            func.get(num-1).apply(null);
        }
    }

    private Boolean CreateFile(){
        System.out.print("ДАААААААА\n");
        return (Boolean) true;
    }

    private Boolean WriteToFile(){
        return true;
    }

    private Boolean ReadFromFile(){
        return true;
    }

    private Boolean DeleteFile(){
        return true;
    }
}
