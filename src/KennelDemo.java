import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
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
        File file;
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of kennel information: ");
        filename = scan.nextLine();
        file = new File(filename);
        do{
            System.out.println("File does not exist, please enter a new filename: ");
            filename = scan.nextLine();
            file = new File(filename);
        }while (!file.exists());
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
                    admitAnimal();
                    break;
                case "2":
                    changeKennelName();
                    break;
                case "3":
                    printAll();
                    break;
                case "4":
                    searchForAnimal();
                    break;
                case "5":
                    removeAnimal();
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
        System.out.println("1 -  add a new Animal ");
        System.out.println("2 -  set up Kennel name");
        System.out.println("3 -  display all animals");
        System.out.println("4 -  search for an animal");
        System.out.println("5 -  remove an animal");
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
        kennel.sortAnimalsByName();
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

    private void removeAnimal() {
        System.out.println("which cat do you want to remove");
        String animalToBeRemoved;
        animalToBeRemoved = scan.nextLine();
        kennel.removeAnimal(animalToBeRemoved);
    }

    private void searchForAnimal() {
        System.out.println("which cat do you want to search for");
        String name = scan.nextLine();
        Animal animal = kennel.search(name);
        if (animal != null) {
            System.out.println(animal.toString());
        } else {
            System.out.println("Could not find cat: " + name);
        }
    }

    private void changeKennelName() {
        String name = scan.nextLine();
        kennel.setName(name);
    }

    private void admitAnimal() {
		boolean sr = false;
        System.out.println("What kind of animal are you adding, car or dog? (C/D)");

        String animal = scan.nextLine();
        do {
            System.out.println("You entered " + animal);
            System.out.print("Please enter C or D: ");
            animal = scan.nextLine(); // Convert input to uppercase to simplify comparison
        } while (!animal.toUpperCase().equals("C") && !animal.toUpperCase().equals("D"));
		System.out
				.println("enter on separate lines: name, owner-name, owner-phone, shares runs?, favourite food, number of times fed");
		String name = scan.nextLine();
		ArrayList<Owner> owners = getOwners();
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
        switch (animal.toUpperCase()){
            case "C":
                Cat newCat = new Cat(name, sr, fav, numTimes);
                for(Owner o: owners){
                    newCat.addOriginalOwner(o);
                }
                kennel.addAnimal(newCat);
                break;
            case "D":
                Dog newDog = new Dog(name, sr, fav, numTimes);
                for(Owner o: owners){
                    newDog.addOriginalOwner(o);
                }
                kennel.addAnimal(newDog);
                break;
        }

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
