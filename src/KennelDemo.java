import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a Kennel
 *
 * @author Lynda Thomas, Chris Loftus and Faisal Rezwan
 * @version 3 (20th February 2023)
 */
public class KennelDemo {
    private String filename; // holds the name of the file
    private Kennel kennel; // holds the kennel
    private Scanner scan; // so we can read from keyboard

    /*
     * Notice how we can make this constructor private, since we only call from main which
     * is in this class. We don't want this class to be used by any other class.
     */
    private KennelDemo() {
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of kennel information: ");
        filename = scan.nextLine();

        kennel = new Kennel();
    }

    /*
     * initialise() method runs from the main and reads from a file
     */
    private void initialise() {
        System.out.println("Using file " + filename);

        try {
            kennel.load(filename);
        } catch (FileNotFoundException e) {
            System.err.println("The file: " + filename + " does not exist. Assuming first use and an empty file." +
                    " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }

    /*
     * runMenu() method runs from the main and allows entry of data etc
     */
    private void runMenu() {
        String response;
        do {
            printMenu();
            System.out.println("What would you like to do:");
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    admitCat();
                    break;
                case "2":
                    changeKennelName();
                    break;
                case "3":
                    printAll();
                    break;
                case "4":
                    searchForCat();
                    break;
                case "5":
                    removeCat();
                    break;
                case "6":
                    setKennelCapacity();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printMenu() {
        System.out.println("1 -  add a new Cat ");
        System.out.println("2 -  set up Kennel name");
        System.out.println("3 -  display all cats");
        System.out.println("4 -  search for a cat");
        System.out.println("5 -  remove a cat");
        System.out.println("6 -  set kennel capacity");
        System.out.println("q - Quit");
    }

    private void setKennelCapacity() {
        System.out.print("Enter max number of cats: ");
        int max = scan.nextInt();
        scan.nextLine();
        kennel.setCapacity(max);
    }

    /*
     * printAll() method runs from the main and prints status
     */
    private void printAll() {
        System.out.println(kennel);
    }

    /*
     * save() method runs from the main and writes back to file
     */
    private void save() {
        try {
            kennel.save(filename);
        } catch (IOException e) {
            System.err.println("Problem when trying to write to file: " + filename);
        }
    }

    private void removeCat() {
        System.out.println("which cat do you want to remove");
        String cattoberemoved;
        cattoberemoved = scan.nextLine();
        kennel.removeCat(cattoberemoved);
    }

    private void searchForCat() {
        System.out.println("which cat do you want to search for");
        String name = scan.nextLine();
        Cat cat = kennel.search(name);
        if (cat != null) {
            System.out.println(cat.toString());
        } else {
            System.out.println("Could not find cat: " + name);
        }
    }

    private void changeKennelName() {
        String name = scan.nextLine();
        kennel.setName(name);
    }

    private void admitCat() {
		boolean sr = false;
		System.out
				.println("enter on separate lines: name, owner-name, owner-phone, shares runs?, favourite food, number of times fed");
		String name = scan.nextLine();
		//ArrayList<Owner> owners = getOwners();
		System.out.println("Can it share a run? (Y/N)");
		String sharesRuns;
		sharesRuns = scan.nextLine().toUpperCase();
		if (sharesRuns.equals("Y")) {
			sr = true;
		}
		System.out.println("What is its favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is it fed a day? (as a number)");
		int numTimes;
		numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
		scan.nextLine();
		Cat newCat = new Cat(name, sr, fav, numTimes);
        ArrayList<Owner> owners = getOwners();
        for(Owner o: owners){
            newCat.addOriginalOwner(o);
        }

        kennel.addCat(newCat);
	}

    private ArrayList<Owner> getOwners() {
        ArrayList<Owner> owners = new ArrayList<Owner>();
        String answer;
        do {
            System.out
                    .println("Enter on separate lines: owner-name owner-phone");
            String ownName = scan.nextLine();
            String ownPhone = scan.nextLine();
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            System.out.println("Another owner (Y/N)?");
            answer = scan.nextLine().toUpperCase();
        } while (!answer.equals("N"));
        return owners;
    }


    // /////////////////////////////////////////////////
    public static void main(String args[]) {
        System.out.println("**********HELLO***********");
        KennelDemo demo = new KennelDemo();
        demo.initialise();
        demo.runMenu();
        demo.printAll();
        // MAKE A BACKUP COPY OF cats.txt JUST IN CASE YOU CORRUPT IT
        demo.save();
        System.out.println("***********GOODBYE**********");
    }
}