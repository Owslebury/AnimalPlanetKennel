import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * To support an individual cat
 * @author Chris Loftus and Faisal Rezwan
 * @version 3 (20th February 2023)
 */
public class Cat extends Animal{
	/**
	 * Default constructor
	 */
	public Cat(){
		this("unknown", false, "unknown", 1);
	}

	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day
	 * @param share Is true if the cat can share a run with other cats, otherwise false
	 * An arraylist of owners is made
	 */
	public Cat(String name, boolean share, String food,
			int mealsPerDay) {
		Name = name;
		originalOwners = new ArrayList<Owner>();
		this.canShare = share;
		this.favFood = food;
		this.foodPerDay = mealsPerDay;
		likesBones = false;

	}
	/**
	 * StringBuilder creates a string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		 sb.append("Cat{name=").append(Name)
				.append(", Original Owner(s) with phone: ").append(originalOwners)
				.append(", Food per day: ").append(" times");
		 return sb.toString();
		//return "Cat name: " + Name + "\n"
				//+ "\nOriginal Owner(s) with phone: " + originalOwners + "\n"
				// + "\nFood per day: " + " times\n";
	}

}
