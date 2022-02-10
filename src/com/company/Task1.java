package com.company;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Task1 extends Task{
    private final File[] paths;
    private final FileSystemView fsv;
    private final String[] menus = {"Вывевсти информацию обо всех дисках", "Вывести информацию об одном диске"};

    public Task1(){
        fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
    }

    @Override
    public void Menu(){
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
        int num = in.nextInt();
        if (num > 0 && num <= menus.length){
            switch (num) {
                case 1 -> {
                    GetAllInformation();
                    WaitForEnter();
                }
                case 2 -> {
                    PrintAllDrives();
                    num = in.nextInt();
                    if (!GetInformationAbtSp(num)) {
                        System.out.print("Диск не найден\n");
                        WaitForEnter();
                        this.Menu();
                    }
                    WaitForEnter();
                }
            }
        }
        else {
            System.out.print("Вводите только цифры от 1 до 2\n");
            WaitForEnter();
            this.Menu();
        }
    }

    public void GetAllInformation(){
        for(File path:paths)
        {
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
            System.out.println("Size: " + path.getTotalSpace()/(1024*1024*1024) + " Gb");
            System.out.println("Type: " + fsv.getSystemTypeDescription(path));
        }
    }

    public void PrintAllDrives(){
        for(int i = 0; i < paths.length; i++)
        {
            System.out.printf("%d) %s\n", i+1, paths[i]);
        }

    }

    public boolean GetInformationAbtSp(int num){
        if (num - 1 < paths.length && num - 1 >= 0){
        System.out.println("Drive Name: " + paths[num - 1]);
        System.out.println("Description: " + fsv.getSystemTypeDescription(paths[num - 1]));
        System.out.println("Size: " + paths[num - 1].getTotalSpace()/(1024*1024*1024) + " Gb");
        System.out.println("Type: " + fsv.getSystemTypeDescription(paths[num - 1]));
        return true;
        }
        else {
            return false;
        }
    }
}
