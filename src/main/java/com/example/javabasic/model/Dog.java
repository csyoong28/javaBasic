package com.example.javabasic.model;

public class Dog extends Animal {

	String sound = "dog sound";
	public void eat() {
		System.out.println("bark, thanks and eat make " +  sound);
	}
	
	public void bark() {
		System.out.println("bark, bark and bark");
	}
}
