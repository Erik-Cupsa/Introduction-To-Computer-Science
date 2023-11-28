package case_study_two;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.omg.CORBA.INV_FLAG;

public class Cat implements Comparable<Cat> {
	private String name;
	private int age;
	
	public Cat() {
		this.name = "Grumpy";
		this.age = 7;
	}
	
	public Cat(String n) {
		this.name = n;
		this.age = 0;
	}
	
	public Cat(String n, int a) {
		this.name = n;
		this.age = a;
	}
	
	public int compareTo(Cat c) {
		if (this.age < c.age) {
			return -1;
		} else if (this.age > c.age) {
			return 1;
		} else {
			return this.name.compareTo(c.name);
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Cat) 
			return ((Cat) obj).age == this.age && ((Cat) obj).name.equals(this.name);
		return false;
	}

	
	public static void sortCats(List<Cat> cats) {
		for (int index = 0; index < cats.size(); index++) {
			Cat c = cats.remove(index);
			int k = 0;
			Iterator<Cat> iter = cats.iterator();
			Cat toCompare = iter.next();
			while (k < index && c.compareTo(toCompare)> 0) {
				k++;
				toCompare = iter.next();
			}
			cats.add(k, c);
		}
	}

	
	public static ArrayList<Cat> getCommonCats(List<Cat> cats1, List<Cat> cats2){
		ArrayList<Cat> sorted = new ArrayList<Cat>();
		for(int i = 0; i <cats1.size(); i++){
			Cat c = cats1.get(i); 
			int k=0; 
			Iterator<Cat> iter = cats2.iterator(); 
			while(iter.hasNext()){
				Cat toCompare = iter.next(); 
				if (c.equals(toCompare)){
					sorted.add(c); 
					iter.remove(); 
					break; 
				}
			}
		}
		return sorted; 
	}
	
	public String toString() {
		return "(" + this.name + ", " + this.age + ")";
	}
	
	public static void main(String[] args) {
		System.out.println("a");
		ArrayList<Cat> myCats = new ArrayList<Cat>();
		myCats.add(new Cat());
		myCats.add(new Cat("Tiger"));
		myCats.add(new Cat("Spritz", 12));
		myCats.add(new Cat("Kitty", 2));
		myCats.add(new Cat("Ginger", 2));
		sortCats(myCats);
		System.out.println(myCats);
		
		
		ArrayList<Cat> otherCats = new ArrayList<Cat>();
		otherCats.add(new Cat());
		otherCats.add(new Cat("Tiger", 8));
		otherCats.add(new Cat("Spritz", 12));
		otherCats.add(new Cat("Kitty", 1));
		otherCats.add(new Cat("Grey", 2));
		otherCats.add(new Cat("Fluffy", 11));
		sortCats(otherCats);
		System.out.println(otherCats);
		
		ArrayList<Cat> common = getCommonCats(myCats, otherCats);
		System.out.println(common);
		
	}
}
