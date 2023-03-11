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
	 *
	 * Share runs is only relevant to cats
	 */
	protected Boolean sharesRuns;
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
		setAnimal("Cat");
		likesBones = false;

	}

}
