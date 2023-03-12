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
import javax.swing.*;

/**
 * This class runs a Kennel
 *
 * @author Lynda Thomas, Chris Loftus and Faisal Rezwan
 * @version 3 (20th February 2023)
 */
public class KennelDemo {
    private boolean finish = false;
    private String filename; // holds the name of the file
    private Kennel kennel; // holds the kennel
    private Scanner scan; // so we can read from keyboard

    /*
     * Notice how we can make this constructor private, since we only call from main which
     * is in this class. We don't want this class to be used by any other class.
     */
    private KennelDemo() {
        JFileChooser fileChooser = new JFileChooser();
        //this ensures that it only selects the file name rather than the directory
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File myFile = null;
        String response;
        //show open dialog shows the dialog to open a file
        int result = fileChooser.showOpenDialog(null);
        //approve option returns number indicating true or false for whether it was approved
        if (result == JFileChooser.APPROVE_OPTION) {
            myFile = fileChooser.getSelectedFile();
            filename = myFile.getName();
        } else {
            while (myFile == null){
                System.out.println("You did not select a file, press q to quit or s to select again");
                scan = new Scanner(System.in);
                response = scan.nextLine().toUpperCase();
                switch (response.toUpperCase()){
                    case "S":
                        result = fileChooser.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION){
                            myFile = fileChooser.getSelectedFile();
                        }
                        else{
                            myFile = null;
                        }
                    case "Q":
                        System.out.println("***********GOODBYE**********");
                        finish = true;
                        break;
                }
                break;
            }
        }
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
            while(response.matches("[1-6Q]") == false) {
                System.out.println("Input is invalid, please enter 1-6 or Q");
                response = scan.nextLine().toUpperCase();
            }
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
        int max = 0;
        while (true) {
            try {
                System.out.print("Enter max number of cats: ");
                max = Integer.parseInt(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter max number of animals: ");
            }
        }
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
        if (kennel.hasAnimal(animalToBeRemoved)== false){
            System.out.println("Kennel does not contain this animal");
        }
        else{
            kennel.removeAnimal(animalToBeRemoved);
        }
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
        Scanner scan = new Scanner(System.in);
        boolean sr = false;
        System.out.println("What kind of animal are you adding, car or dog? (C/D)");

        String animal;
        do {
            animal = scan.nextLine().toUpperCase();
            if (!animal.equals("C") && !animal.equals("D")) {
                System.out.print("Invalid input. Please enter C or D: ");
            }
        } while (!animal.equals("C") && !animal.equals("D"));

        System.out.println("Enter on separate lines: name, owner-name, owner-phone, shares runs?, favourite food, number of times fed");
        String name = scan.nextLine();
        String ownerName = scan.nextLine();
        String ownerPhone = scan.nextLine();

        System.out.println("Can it share a run? (Y/N)");
        String sharesRuns;
        do {
            sharesRuns = scan.nextLine().toUpperCase();
            if (!sharesRuns.equals("Y") && !sharesRuns.equals("N")) {
                System.out.print("Invalid input. Please enter Y or N: ");
            }
        } while (!sharesRuns.equals("Y") && !sharesRuns.equals("N"));

        if (sharesRuns.equals("Y")) {
            sr = true;
        }

        System.out.println("What is its favourite food?");
        String fav = scan.nextLine();

        System.out.println("How many times is it fed a day? (as a number)");
        int numTimes;
        while (true) {
            try {
                numTimes = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                scan.next();
            }
        }
        scan.nextLine();

        ArrayList<Owner> owners = getOwners();
        switch (animal) {
            case "C":
                Cat newCat = new Cat(name, sr, fav, numTimes);
                for (Owner o : owners) {
                    newCat.addOriginalOwner(o);
                }
                kennel.addAnimal(newCat);
                break;
            case "D":
                Dog newDog = new Dog(name, sr, fav, numTimes);
                for (Owner o : owners) {
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
        if (demo.finish == false){
            demo.initialise();
            demo.runMenu();
            demo.printAll();
            // MAKE A BACKUP COPY OF cats.txt JUST IN CASE YOU CORRUPT IT
            demo.save();
            System.out.println("***********GOODBYE**********");
        }
    }
}
