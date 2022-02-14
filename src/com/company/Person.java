package com.company;

import java.io.Serializable;

public class Person implements Serializable {
    int age;
    String name;
    String surname;

    public Person(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }


    public String ToString(){
        return "Имя: " + name + " Фамилия: " + surname + " Возраст: " + age;
    }
}
