package com.company;

import java.io.*;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Task3 extends Task{

    File file;
    private final String curDirectory = "D://Maxim//ЯП//OS//lab1//";

    public Task3(){
        menus = new String[]{"Записать в файл", "Вывести информацию из файла", "Удалить файл"};
        func = Arrays.asList((num)->WriteToFile(),(num)->ReadFromFile(),(num)->DeleteFile());
    }

    @Override
    public void Menu() {
        for (int i = 0; i < menus.length;i++){
            System.out.printf("%d) %s\n", i+1, menus[i]);
        }
    }

    public void CreateFile(String newfile){
        file = new File(curDirectory + newfile);
        try
        {
            boolean created = file.createNewFile();
            if(created)
                System.out.printf("Файл %s был успешно создан\n",newfile);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Boolean WriteToFile(){
        PrintAllFiles();
        System.out.print("Введите название файла для записи в него: ");
        String temp = in.nextLine();
        if (!(file = new File(curDirectory + temp)).exists()){
            if (!temp.contains(".json")){
                temp += ".json";
            }
            CreateFile(temp);
        }
        Gson gson = new Gson();
        String json = gson.toJson(CreatePerson());
        try(FileOutputStream fos = new FileOutputStream(curDirectory + temp))
        {
            byte[] buffer = json.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("Информация в файл была записана");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public Boolean ReadFromFile(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        PrintAllFiles();
        System.out.print("Введите название файла для вывода: ");
        String temp = in.nextLine();
        if (!temp.contains(".json")){
            temp += ".json";
        }
        try(FileInputStream fin=new FileInputStream(curDirectory + temp))
        {
            StringBuilder content = new StringBuilder();
            int i=-1;
            while((i=fin.read())!=-1){
                content.append((char)i);
            }
            Person person = gson.fromJson(content.toString(), Person.class);
            System.out.printf("%s\n",person.ToString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public Boolean DeleteFile(){
        PrintAllFiles();
        System.out.print("Введите название файла для удаления: ");
        String temp = in.nextLine();
        if (!temp.contains(".json")){
            temp += ".json";
        }
        file = new File(curDirectory+temp);
        if(file.delete()){
            System.out.printf("%s файл удален\n",curDirectory+temp);
        }
        else System.out.printf("Файла %s не обнаружено",curDirectory+temp);
        return true;
    }

    private void PrintAllFiles(){
        File dir = new File("D://Maxim//ЯП//OS//lab1");

        if(dir.isDirectory())
        {
            for(File item : dir.listFiles()){

                if(!item.isDirectory() && item.getName().contains(".json")){
                    System.out.println(item.getName());
                }

            }
        }
    }

    private Person CreatePerson(){
        System.out.print("Введите имя: ");
        String name = in.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = in.nextLine();
        System.out.print("Введите возраст: ");
        int age = in.nextInt();

        return new Person(name, surname, age);
    }

}
