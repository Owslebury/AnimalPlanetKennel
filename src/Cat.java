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
	 * An arraylist of owners is made
	 */
	public Cat(Animal base) {
		baseAnimal = base;
		originalOwners = new ArrayList<Owner>();
		setAnimal("Cat");
	}

	/**
	 * Loads individual cat methods from cat file when given the cat name
	 * @param catName name to search for
	 * @return
	 */
	public Cat loadCatMethods(String catName){
		try (FileReader fr = new FileReader("cat.txt");
			 BufferedReader br = new BufferedReader(fr);
			 Scanner infile = new Scanner(br)) {

			// Use the delimiter pattern so that we don't have to clear end of line
			// characters after doing a nextInt or nextBoolean
			infile.useDelimiter("\r?\n|\r");
			while (infile.hasNextLine()) {
				String tempName = infile.nextLine();
				if (tempName.equalsIgnoreCase(catName)) {
					sharesRuns = infile.nextBoolean();
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

	/**
	 * Saves individual cat methods to cat.txt
	 */
	public void saveCat(){
		try{
			//filewriter appends to the end of the file so multiple dogs can be added.
			FileWriter writer = new FileWriter("cat.txt", true);
			writer.write("\n" + baseAnimal.getName() + "\n");
			writer.write(sharesRuns + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println("Error saving cat attributes");
		}

	}

	/**
	 * tostring method with cat specific attributes
	 * @return
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseAnimal.AnimalName)
				.append("{name=").append(baseAnimal.Name)
				.append(", Original Owner(s) with phone: ").append(baseAnimal.originalOwners)
				.append(", Food per day: ").append(baseAnimal.foodPerDay).append(" times")
				.append(", Shares runs: ").append(sharesRuns);

		return sb.toString();
	}

}
