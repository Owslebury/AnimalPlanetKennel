import java.io.BufferedReader;
import java.io.FileReader;
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

	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day
	 * @param share Is true if the cat can share a run with other cats, otherwise false
	 * An arraylist of owners is made
	 */
	public Cat(Animal base) {
		originalOwners = new ArrayList<Owner>();
		setAnimal("Cat");
	}
	public void loadCatMethods(){
		try (FileReader fr = new FileReader("cat.txt");
			 BufferedReader br = new BufferedReader(fr);
			 Scanner infile = new Scanner(br)) {

			// Use the delimiter pattern so that we don't have to clear end of line
			// characters after doing a nextInt or nextBoolean
			infile.useDelimiter("\r?\n|\r");
			sharesRuns = infile.nextBoolean();
		}
		catch (Exception e){

		}

	}

}
