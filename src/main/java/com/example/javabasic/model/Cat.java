package com.example.javabasic.model;

public class Cat extends Animal {

	String sound = "cat sound";
	public void eat() {
		System.out.println("meow, thanks and eat make " +  sound);
	}
	
	public void bark() {
		System.out.println("meow, meow and meow");
	}
}
