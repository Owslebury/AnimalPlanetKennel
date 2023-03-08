import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * To support an individual cat
 * @author Chris Loftus and Faisal Rezwan
 * @version 3 (20th February 2023)
 */
public class Cat {

	private ArrayList<Owner> originalOwners;
	private boolean canShare;
	private String catName;
	private String favFood;
	private int foodPerDay;

	/**
	 * Default constructor
	 */
	public Cat(){
		this("unknown", false, "unknown", 1);
	}

	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param owners A list of original owners: a copy is made
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day
	 * @param canShare Is true if the cat can share a run with other cats, otherwise false
	 */
	public Cat(String name, boolean share, String food,
			int mealsPerDay) {
		catName = name;
		originalOwners = new ArrayList<Owner>();

		this.canShare = share;
		this.favFood = food;
		this.foodPerDay = mealsPerDay;
	}

	public String getName() {
		return catName;
	}

	public void setName(String newName) {
		catName = newName;
	}
	
	/**
	 * Returns a copy of the original owners
	 * @return A copy of the original owners as an array
	 */
	public Owner[] getOriginalOwners(){
		Owner[] result = new Owner[originalOwners.size()];
		result = originalOwners.toArray(result);
		return result;
	}

	/**
	 * To add an original owner
	 * @param o An original owner
	 */
	public void addOriginalOwner(Owner o){
		originalOwners.add(o);
	}

	/**
	 * Does the cat like to share a walk with other cats?
	 * @return true if it does
	 */
	public boolean getcanShare() {
		return canShare;
	}
	/**
	 * How many times a day to feed the cat
	 * @param feeds The number of feeds per day
	 */
	public void setFeedsPerDay(int feeds){
		foodPerDay = feeds;
	}
	
	/**
	 * The number of feeds per day the cat is fed
	 * @return The number of feeds per day
	 */
	public int getFeedsPerDay(){
		return foodPerDay;
	}
	
	/**
	 * What's his favourite food?
	 * @param food The food it likes
	 */
	public void setFavouriteFood(String food){
		favFood = food;
	}
	
	/**
	 * The food the cat likes to eat
	 * @return The food 
	 */
	public String getFavouriteFood(){
		return favFood;
	}

	/**
	 * Reads in information about the cat from the file
	 */
	public void load(Scanner infile){

		catName = infile.next();
		int numOwners = infile.nextInt();
		originalOwners = new ArrayList<>();
		for (int oCount = 0; oCount < numOwners; oCount++) {
			String name = infile.next();
			String phone = infile.next();
			Owner owner = new Owner(name, phone);
			originalOwners.add(owner);
		}
		canShare = infile.nextBoolean();
		foodPerDay = infile.nextInt();
		favFood = infile.next();
	}

	public void save(PrintWriter pw){
		pw.println(catName);
		pw.println(originalOwners.size());
		for (Owner o : originalOwners) {
			pw.println(o.getName());
			pw.println(o.getPhone());
		}
		pw.println(canShare);
		pw.println(foodPerDay);
		pw.println(favFood);
	}

	/**
	 * Note that this only compares equality based on a
	 * cat's name.
	 * @param obj the other cat to compare against.
	 */
	@Override
	public boolean equals(Object obj) { // Generated by IDE to be more robust
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (catName == null) {
			if (other.catName != null)
				return false;
		} else if (!catName.equals(other.catName))
			return false;
		return true;
	}

	/**
	 * A basic implementation to just return all the data in string form
	 */
	public String toString() {
		return "Cat name: " + catName + "\n"
				+ "\nOriginal Owner(s) with phone: " + originalOwners + "\n"
				+ "\nFood per day: " + " times\n";
	}

}