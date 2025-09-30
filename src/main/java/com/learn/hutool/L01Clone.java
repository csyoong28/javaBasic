package com.learn.hutool;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
public class L01Clone {

    public static void main(String[] args) {
        // 创建猫猫对象
        Cat cat = new Cat();
        // 克隆猫猫对象
        Cat clonedCat = cat.clone();
        clonedCat.name = "kitty"; // 修改克隆对象的属性
        System.out.println("Original Cat: " + cat.name + ", Age: " + cat.age);
        System.out.println("Cloned Cat: " + clonedCat.name + ", Age: " + clonedCat.age);

        // 创建狗狗对象
        Dog dog = new Dog();
        // 克隆狗狗对象
        Dog clonedDog = dog.clone();
        clonedDog.name = "doggy"; // 修改克隆对象的属性
        System.out.println("Original Dog: " + dog.name + ", Age: " + dog.age);
        System.out.println("Cloned Dog: " + clonedDog.name + ", Age: " + clonedDog.age);
    }




    /**
     * 猫猫类，使用实现Cloneable方式
     * @author Looly
     *
     */
    private static class Cat implements Cloneable<Cat>{
        private String name = "miaomiao";
        private int age = 2;

        @Override
        public Cat clone() {
            try {
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }
    }

    /**
     * 狗狗类，用于继承CloneSupport类
     * @author Looly
     *
     */
    private static class Dog extends CloneSupport<Dog> {
        private String name = "wangwang";
        private int age = 3;
    }
}

