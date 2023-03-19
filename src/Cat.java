import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
	Animal baseAnimal;

	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day
	 * @param share Is true if the cat can share a run with other cats, otherwise false
	 * An arraylist of owners is made
	 */
	public Cat(Animal base) {
		baseAnimal = base;
		originalOwners = new ArrayList<Owner>();
		setAnimal("Cat");
		loadCatMethods(base.Name);
	}
	public Cat loadCatMethods(String catName){
		try (FileReader fr = new FileReader("cat.txt");
			 BufferedReader br = new BufferedReader(fr);
			 Scanner infile = new Scanner(br)) {

			// Use the delimiter pattern so that we don't have to clear end of line
			// characters after doing a nextInt or nextBoolean
			infile.useDelimiter("\r?\n|\r");
			sharesRuns = infile.nextBoolean();
			while (infile.hasNextLine()) {
				String tempName = infile.nextLine();
				sharesRuns = infile.nextBoolean();
				if (tempName.equals(catName)) {
					infile.close();
					return this;
				}
			}
			System.out.println("Cat not found!");
			return null;
		}
		catch (Exception e){
			System.out.println("Error searching for cat");
			return null;
		}
	}
	public void saveCat(){
		try{
			//filewriter appends to the end of the file so multiple dogs can be added.
			FileWriter writer = new FileWriter("cat.txt", true);
			writer.write(baseAnimal.getName() + "\n");
			writer.write(sharesRuns + "\n");
		}
		catch (Exception e){

		}

	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseAnimal.AnimalName)
				.append("{name=").append(baseAnimal.Name)
				.append(", Original Owner(s) with phone: ").append(baseAnimal.originalOwners)
				.append(", Food per day: ").append(baseAnimal.foodPerDay).append(" times");
		return sb.toString();
	}

}
