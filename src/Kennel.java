import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To model a Kennel - a collection of cats
 *
 * @author Chris Loftus and Faisal Rezwan
 * @version 2 (20th February 2019)
 */
public class Kennel {
    private String name;
    private ArrayList<Cat> cats;
    private int nextFreeLocation;
    private int capacity;

    /**
     * Creates a kennel with a default size 20
     */
    public Kennel() {
        this(20);
    }

    /**
     * Create a kennel
     *
     * @param maxNoCats The capacity of the kennel
     */
    public Kennel(int maxNoCats) {
        nextFreeLocation = 0; // no Cats in collection at start
        capacity = maxNoCats;
        cats = new ArrayList<Cat>(capacity); // set up default. This can
        // actually be exceeded
        // when using ArrayList but we
        // won't allow that
        // to happen.
    }

    /**
     * This method sets the value for the name attribute. The purpose of the
     * attribute is: The name of the kennel e.g. "CatsRUs"
     *
     * @param theName
     */
    public void setName(String theName) {
        name = theName;
    }

    /**
     * Set the size of the kennel
     *
     * @param capacity The max cats we can house
     */
    public void setCapacity(int capacity) {
        // This should really check to see if we already have cats
        // in the kennel and reducing the capacity would lead to evictions!
        this.capacity = capacity;
    }

    /**
     * Maximum capacity of the kennels
     *
     * @return The max size of the kennel
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * This method gets the value for the name attribute. The purpose of the
     * attribute is: The name of the Kennel e.g. "CatsRUS"
     *
     * @return String The name of the kennel
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the number of cats in a kennel
     *
     * @return int Current number of cats in the kennel
     */
    public int getNumOfCats() {
        return nextFreeLocation;
    }

    /**
     * Enables a user to add a Cat to the Kennel
     *
     * @param theCat A new cat to home
     */
    public void addCat(Cat theCat) {
        if (nextFreeLocation >= capacity) {
            System.out.println("Sorry kennel is full - cannot add team");
            return;
        }
        // we add in the position indexed by nextFreeLocation
        // This starts at zero
        cats.add(theCat);

        // now increment index ready for next one
        nextFreeLocation = nextFreeLocation + 1;
    }

    /**
     * Enables a user to delete a cat from the Kennel.
     *
     * @param who The cat to remove
     */
    public void removeCat(String who) {
        Cat which = null;
        // Search for the cat by name
        for (Cat c : cats) {
            if (who.equals(c.getName())) {
                which = c;
            }
        }
        if (which != null) {
            cats.remove(which); // Requires that Cat has an equals method
            System.out.println("removed " + who);
            nextFreeLocation = nextFreeLocation - 1;
        } else {
            System.err.println("cannot remove - not in kennel");
        }
    }

    /**
     * @return String showing all the information in the kennel
     */
    public String toString() {
        String results = "Data in Kennel " + name + " is: \n";
        for (Cat c : cats) {
            results = results + c.toString() + "\n";
        }
        return results;
    }

    /**
     * Returns an array of the inmates in the kennels
     *
     * @return An array of the correct size
     */
    public Cat[] obtainAllAnimals() {

        // ENTER CODE HERE
        // (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF ANIMALS: CATS AND DOGS)
        // SEE Cat.getOriginalOwners METHOD FOR SIMILAR CODE

        Cat[] result = null;
        return result;
    }


    /**
     * Searches for and returns the inmate, if found
     * @param name The name of the inmate
     * @return The inmate or else null if not found
     */
    public Cat search(String name) {

        // ENTER CODE HERE (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF ANIMALS: CATS AND DOGS)
        for (Cat item: cats){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
        //Cat result = null;

        //return result;
    }

    /**
     * Reads in Kennel information from the file
     *
     * @param infileName The file to read from
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(String infileName) throws IOException{
        // Using try-with-resource. We will cover this in Lecture 8, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.
        // But if you don't understand it now, don't worry about it.
        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            name = infile.next();
            capacity = infile.nextInt();

            while (infile.hasNext()) {
                Cat cat = new Cat();
                cat.load(infile);
                this.addCat(cat);
            }
        }
    }

    /**
     * Saves the kennel information
     *
     * @param filename The file to save to
     * @throws IOException If some IO error occurs
     */
    public void save(String filename) throws IOException {
        // Using try-with-resource. We will cover this in Lecture 8, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.
        // But if you don't understand it now, don't worry about it.
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);) {

            outfile.println(name);
            outfile.println(capacity);
            for (Cat c : cats) {
                c.save(outfile);
            }
        }
    }
}
