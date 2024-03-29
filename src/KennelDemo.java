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
 * @version last edit 22/3/23
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

    /**
     * User selects a file and if it is valid it will open, else it continue prompting or the user can quit
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

    /**
     * Printmenu prints the menu options to the console
     */

    private void printMenu() {
        System.out.println("1 -  add a new Animal ");
        System.out.println("2 -  set up Kennel name");
        System.out.println("3 -  display all animals");
        System.out.println("4 -  search for an animal");
        System.out.println("5 -  remove an animal");
        System.out.println("6 -  set kennel capacity");
        System.out.println("q - Quit (save)");
    }

    /**
     * setKennelCapacity sets the maximum number of animals to the kennel
     */

    private void setKennelCapacity() {
        int max = 0;
        while (true) {
            try {
                System.out.print("Enter max number of animals: ");
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

    /**
     * Removeanimal removes an animal from the kennel by name
     */

    private void removeAnimal() {
        System.out.println("which animal do you want to remove");
        String animalToBeRemoved;
        animalToBeRemoved = scan.nextLine();
        if (kennel.hasAnimal(animalToBeRemoved)== false){
            System.out.println("Kennel does not contain this animal");
        }
        else{
            kennel.removeAnimal(animalToBeRemoved);
        }
    }

    /**
     * SearchForAnimal searches for animal in kennel and prints its tostring method
     * if the imagefile is not nothing, the user has the option to open the image in a prompt
     */

    private void searchForAnimal() {
        System.out.println("enter animal name to search for");
        String name = scan.nextLine();
        Animal animal = kennel.search(name);
        if (animal != null) {
            switch(animal.getAnimalName()){
                case "Dog":
                    Dog newDog = new Dog(animal);
                    newDog.loadDogMethods(animal.Name);
                    System.out.println(newDog.toString());
                    break;
                case "Cat":
                    Cat newCat = new Cat(animal);
                    newCat.loadCatMethods(animal.Name);
                    System.out.println(newCat.toString());
                    break;
            }
            if (animal.imageFile.equals("none") == false && animal.imageFile != null){
                boolean input;
                    System.out.println("This animal has one matching image, show? (Y/N)");
                    input = GetYesOrNo();
                if (input == true){
                    JFrame dialog = new JFrame();
                    ImageIcon image = new ImageIcon(animal.imageFile);
                    JLabel label = new JLabel(image);
                    dialog.getContentPane().add(label);
                    dialog.pack();
                    dialog.setVisible(true);
                }


            }
        } else {
            System.out.println("Could not find animal: " + name);
        }
    }

    /**
     * changes the name of the kennel
     */

    private void changeKennelName() {
        System.out.println("Please enter a new name for the kennel");
        String name = scan.nextLine();
        kennel.setName(name);
    }

    /**
     * Takes in attributes for animals, as well as attributes for specific animals which it saves
     * to their specific text file
     */

    private void admitAnimal() {
        if (kennel.KennelFull() == false) {
            Scanner scan = new Scanner(System.in);
            Animal animalBase = new Animal();
            System.out.println("What kind of animal are you adding, cat or dog? (C/D)");

            String animal;
            do {
                animal = scan.nextLine().toUpperCase();
                if (animal.toUpperCase().equals("C") == false && animal.toUpperCase().equals("D") == false) {
                    System.out.print("Invalid input. Please enter C or D: ");
                }
            } while (animal.toUpperCase().equals("C") == false && animal.toUpperCase().equals("D") == false);

            if (animal.toUpperCase().equals("C")) {
                animalBase.AnimalName = "Cat";
            } else if (animal.toUpperCase().equals("D")) {
                animalBase.AnimalName = "Dog";
            } else {
                System.out.println("Please only enter a cat or dog");
            }

            System.out.println("Enter on separate lines: name, owner-name, owner-phone, favourite food, number of times fed");
            animalBase.setName(scan.nextLine());
           while (animalBase.getName().isEmpty()){
                System.out.println("Please enter something");
               animalBase.setName(scan.nextLine());
            }
            ArrayList<Owner> owners = getOwners();


            System.out.println("What is its favourite food?");
            animalBase.setFavouriteFood(scan.nextLine());
            while (animalBase.getFavouriteFood().isEmpty()){
                System.out.println("Please enter a favourite food");
                animalBase.setFavouriteFood(scan.nextLine());
            }

            System.out.println("How many times is it fed a day? (as a number)");
            int numTimes;
            while (true) {
                try {
                    numTimes = scan.nextInt();
                    if (numTimes > 0){
                        break;
                    }
                } catch (Exception e) {
                    System.out.print("Invalid input. Please enter a number: ");
                    scan.next();
                }
            }
            scan.nextLine();
            animalBase.setFeedsPerDay(numTimes);
            for (Owner o : owners) {
                animalBase.addOriginalOwner(o);
            }
            System.out.println("Add image file? (Y/N)");
            Boolean input = GetYesOrNo();

            if (input == true){
                System.out.println("Please enter the filename of the image");
                String filename = scan.nextLine();
                File file = new File(filename);
                if (file.exists()){
                    animalBase.imageFile = filename;
                }
                else{
                    System.out.println("Sorry, file does not exist");
                }
            }
            kennel.addAnimal(animalBase);

            switch (animal) {
                case "C":
                    Cat newCat = new Cat(animalBase);
                    System.out.println("Can it share a run? (Y/N)");
                    newCat.sharesRuns = GetYesOrNo();
                    newCat.saveCat();
                    break;
                case "D":
                    Dog newDog = new Dog(animalBase);
                    System.out.println("Does it like bones? (Y/N)");
                    newDog.likesBones = GetYesOrNo();
                    System.out.println("Does it need a walk? (Y/N)");
                    newDog.needsWalk = GetYesOrNo();

                    System.out.println("Please enter any other diet info about the dog");
                    newDog.DietInfo = (scan.nextLine());
                    newDog.saveDog();
                    break;
            }
        }
    }

    /**
     * This is a boolean function for yes or no options for exclusive cats/dog methods
     * @return
     */
    private boolean GetYesOrNo(){
        boolean choice = false;
        String input;
        do {
            input = scan.nextLine().toUpperCase();
            if (!input.equals("Y") && !input.equals("N")) {
                System.out.print("Invalid input. Please enter Y or N: ");
            }
        } while (!input.equals("Y") && !input.equals("N"));

        if (input.equals("Y")) {
            choice = true;
        }
        return choice;
    }

    /**
     * gets the names and phone numbers of as many owners as wanted
     * @return
     */

    private ArrayList<Owner> getOwners() {
        ArrayList<Owner> owners = new ArrayList<Owner>();
        Boolean answer;
        do {
            System.out
                    .println("Enter on separate lines: owner-name owner-phone");
            String ownName = scan.nextLine();
            while (ownName.isEmpty()){
                System.out.println("Please enter an owner name");
                ownName = scan.nextLine();
            }
            String ownPhone = scan.nextLine();
            while (ownPhone.isEmpty() || isNumber(ownPhone) == false){
                System.out.println("Please enter the owners phone number");
                ownPhone = scan.nextLine();
            }
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            System.out.println("Another owner (Y/N)?");
            answer = GetYesOrNo();
        } while (answer == true);
        return owners;
    }

    public static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Please only enter a number");
            return false;
        }
    }


    // /////////////////////////////////////////////////
    public static void main(String args[]) {
        System.out.println("**********HELLO***********");
        KennelDemo demo = new KennelDemo();
        if (demo.finish == false){
            try{
                demo.initialise();
            }
            catch (Exception e){
                System.out.println("This file is not in the correct format!");
                main(args);
                return;
            }

            demo.runMenu();
            demo.printAll();
            // MAKE A BACKUP COPY OF cats.txt JUST IN CASE YOU CORRUPT IT
            demo.save();
            System.out.println("***********GOODBYE**********");
        }
    }
}
