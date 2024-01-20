package com.example.javabasic;

import com.example.javabasic.model.Animal;
import com.example.javabasic.model.Cat;
import com.example.javabasic.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class Java13GenericListMain {
    public static void main(String[] args) {
        Java13GenericList<Integer> java13GenericList = new Java13GenericList<>();
        java13GenericList.add(1);
        java13GenericList.add(2);

        System.out.println(java13GenericList.get(1));       //print 2
    }

    public static void invariance() {
        List<Animal> zoo = new ArrayList<>();
        zoo.add(new Dog());
        zoo.add(new Cat());
        //reading behavior
        Animal animal = zoo.get(0);

        List<Dog> dogList = new ArrayList<>();
        // zoo = dogList;   //error
    }

    public static void printUsers(Java13GenericList<? extends Animal> animal) {
        Animal animal1 = animal.get(0);
      //  Dog dog = animal.get(0);
      //  animal.add(animal1);        //cannot add because the wildcard causing actual type unknown
    }

    public static void printUsers2(Java13GenericList<? super Animal> animal) {
        Object object = animal.get(0);
        Animal animal1 = new Dog();
        Dog dog1 = new Dog();
        animal.add(animal1);
        animal.add(dog1);
       // animal.add(object); //cannot add Object
    }


}
