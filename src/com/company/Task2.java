package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Task2 extends Task {
    private File file;
    private final String curDirectory = "D://Maxim//ЯП//OS//lab1//";

    public Task2(){
        menus = new String[]{"Создать файл", "Записать в файл", "Вывести информацию из файла", "Удалить файл"};
        func = Arrays.asList((num)->CreateFile(),(num)->WriteToFile(),(num)->ReadFromFile(),(num)->DeleteFile());
    }

    @Override
    public void Menu(){
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
    }

    public Boolean CreateFile(){
        System.out.print("Введите название файла: ");
        String newfile = in.nextLine();
        file = new File(curDirectory + newfile);
        try
        {
            boolean created = file.createNewFile();
            if(created)
                System.out.printf("Файл %s был успешно создан\n",newfile);
            return true;
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Boolean WriteToFile(){
        PrintAllFiles(curDirectory);
        System.out.print("Введите название файла для записи в него: ");
        String temp = in.nextLine();
        System.out.print("Введите содержимое для записи в него: ");
        String text = in.nextLine();
        try(FileOutputStream fos=new FileOutputStream(curDirectory + temp))
        {
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("Информация в файл была записана");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return true;
    }

    public Boolean ReadFromFile(){
        PrintAllFiles(curDirectory);
        System.out.print("Введите название файла для вывода: ");
        String temp = in.nextLine();
        try(FileInputStream fin=new FileInputStream(curDirectory + temp))
        {
            int i;
            while((i=fin.read())!=-1){
                System.out.print((char)i);
            }
            System.out.print("\n");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public Boolean DeleteFile(){
        PrintAllFiles(curDirectory);
        System.out.print("Введите название файла для удаления: ");
        String temp = in.nextLine();
        file = new File(curDirectory+temp);
        if(file.delete()){
            System.out.printf("%s файл удален\n",curDirectory+temp);
        }
        else System.out.printf("Файла %s не обнаружено",curDirectory+temp);
        return true;
    }


}
