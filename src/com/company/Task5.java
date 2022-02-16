package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Task5 extends Task{

    File file;
    private final String curDirectory = "D://Maxim//ЯП//OS//lab1//";

    public Task5(){
        menus = new String[]{"Создать архив","Добавить файл в архив", "Разархивировать файл", "Удалить файл"};
        func = Arrays.asList((num)->CreateArchive(),(num)->AddToArchive(),(num)->ReadFromArchive(),(num)->DeleteArchive());
    }

    private Boolean DeleteArchive() {
        PrintAllFiles(curDirectory, ".zip");
        System.out.print("Введите название файла для удаления: ");
        String temp = in.nextLine();
        if (!temp.contains(".zip")){
            temp += ".zip";
        }
        file = new File(curDirectory+temp);
        if(file.delete()){
            System.out.printf("%s файл удален\n",curDirectory+temp);
        }
        else System.out.printf("Файла %s не обнаружено",curDirectory+temp);
        return true;
    }


    private Boolean ReadFromArchive() {
        PrintAllFiles(curDirectory, ".zip");
        System.out.print("Введите название архива: ");
        String temp = in.nextLine();
        if (!temp.contains(".zip")){
            temp += ".zip";
        }
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(curDirectory+temp)))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName(); // получим название файла
                // распаковка
                FileOutputStream fout = new FileOutputStream(curDirectory + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                    System.out.print((char)c);
                }
                System.out.print("\n");
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return true;
    }

    private Boolean AddToArchive() {
        PrintAllFiles(curDirectory, ".zip");
        System.out.print("Введите название архива: ");
        String temp = in.nextLine();
        if (!temp.contains(".zip")){
            temp += ".zip";
        }
        PrintAllFiles(curDirectory);
        System.out.print("Введите название файла, чтобы добавить в архив: ");
        String filename = in.nextLine();
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(curDirectory+temp));
            FileInputStream fis= new FileInputStream(curDirectory+filename))
        {
            ZipEntry entry1=new ZipEntry(filename);
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Boolean CreateArchive() {
        System.out.print("Введите название файла: ");
        String temp = in.nextLine();
        if (!temp.contains(".zip")){
            temp += ".zip";
        }
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(curDirectory+temp)))
        {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    @Override
    public void Menu() {
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
    }


}
