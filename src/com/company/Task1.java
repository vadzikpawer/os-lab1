package com.company;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;

public class Task1 extends Task{
    private final File[] paths;
    private final FileSystemView fsv;

    public Task1(){
        fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
        menus = new String[]{"Вывести информацию обо всех дисках", "Вывести информацию об одном диске"};
        func = Arrays.asList((num)->GetAllInformation(),(num)->GetInformationAbtSp());
    }

    @Override
    public void Menu(){
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
    }

    public Boolean GetAllInformation(){
        for(File path:paths) {
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
            System.out.println("Size: " + path.getTotalSpace() / (1024 * 1024 * 1024) + " Gb");
            System.out.println("Type: " + fsv.getSystemTypeDescription(path));
        }
        return true;
    }

    public void PrintAllDrives(){
        for(int i = 0; i < paths.length; i++)
        {
            System.out.printf("%d) %s\n", i+1, paths[i]);
        }
    }

    public Boolean GetInformationAbtSp(){
        PrintAllDrives();
        int num = in.nextInt();
        if (num - 1 < paths.length && num - 1 >= 0){
        System.out.println("Drive Name: " + paths[num - 1]);
        System.out.println("Description: " + fsv.getSystemTypeDescription(paths[num - 1]));
        System.out.println("Size: " + paths[num - 1].getTotalSpace()/(1024*1024*1024) + " Gb");
        System.out.println("Type: " + fsv.getSystemTypeDescription(paths[num - 1]));
        return true;
        }
        else {
            System.out.print("Диск не найден\n");
            this.Menu();
            return false;
        }
    }
}
