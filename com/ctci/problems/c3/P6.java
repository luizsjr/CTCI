package com.ctci.problems.c3;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class P6 {

	public abstract class Animal {
		public String name;
		public Calendar arrivalDate;
		
		
		public Animal(String name) {
			this.name = name;
			this.arrivalDate = Calendar.getInstance();
		}
		
		@Override
		public String toString() {
			return MessageFormat.format("{0} {1}, arrived on {2}", this.getClass().getSimpleName(), this.name, this.arrivalDate.getTimeInMillis());
		}
	}
	
	public class Cat extends Animal {
		public Cat(String name) {
			super(name);
		}
	}
	
	public class Dog extends Animal {
		public Dog(String name) {
			super(name);
		}
	}
	
	private LinkedList<Dog> dogs;
	private LinkedList<Cat> cats;
	
	public P6() {
		dogs = new LinkedList<>();
		cats = new LinkedList<>();
	}
	
	public void enqueueCat(String name) {
		Cat cat = new Cat(name);
		cats.add(cat);
	}
	
	public void enqueueDog(String name) {
		Dog dog = new Dog(name);
		dogs.add(dog);
	}
	
	public Animal dequeueCat() {
		return cats.removeFirst();
	}
	
	public Animal dequeueDog() {
		return dogs.removeFirst();
	}
	
	public Animal dequeueAny() {
		if (cats.isEmpty() && dogs.isEmpty()) {
			throw new NoSuchElementException("No animals in the shelter");
		}
		if (dogs.isEmpty()) {
			return cats.removeFirst();
		}
		
		if(cats.isEmpty()) {
			return dogs.removeFirst();
		}
		Animal oldestDog = dogs.getFirst();
		Animal oldestCat = cats.getFirst();
		if (oldestDog.arrivalDate.before(oldestCat.arrivalDate)) {
			return dogs.removeFirst();
		} else {
			return cats.removeFirst();
		}
	}
	
	public static void main(String[] args) {
		P6 problem = new P6();
		problem.enqueueDog("Kirk");
		problem.enqueueDog("Bozo");
		problem.enqueueCat("Joe");
		problem.enqueueCat("Thereza");
		problem.enqueueDog("Spark");
		problem.enqueueCat("Bonny");
		System.out.println(problem.dequeueAny()); // Kirk
		System.out.println(problem.dequeueCat()); // Joe
		System.out.println(problem.dequeueDog()); // Bozo
		System.out.println(problem.dequeueAny()); // Thereza
		System.out.println(problem.dequeueCat()); // Bonny
		System.out.println(problem.dequeueDog()); // Spark
		System.out.println(problem.dequeueAny()); // No animals in the shelter
		

	}

}
