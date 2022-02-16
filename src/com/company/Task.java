package com.company;

import java.io.Console;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

public abstract class Task {
    public String[] menus;
    public List<Function<Void, Boolean>> func;
    Scanner in = new Scanner(System.in);

    public abstract void Menu();

    public void PrintAllFiles(String directory, String... filter){
        File dir = new File(directory);

        if(dir.isDirectory())
        {
            for(File item : Objects.requireNonNull(dir.listFiles())){

                if(!item.isDirectory() && filter.length==0){
                    System.out.println(item.getName());
                }
                else if (!item.isDirectory() && filter.length!= 0 && item.getName().contains(filter[0])){
                    System.out.println(item.getName());
                }

            }
        }
    }
}
