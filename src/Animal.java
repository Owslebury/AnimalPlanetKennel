import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Animal {
    protected ArrayList<Owner> originalOwners;
    protected boolean canShare;
    protected String Name;
    protected String favFood;
    protected int foodPerDay;
    protected Boolean likesBones;
    protected Boolean sharesRuns;
    public String getName() {
        return Name;
    }
    public void setName(String newName) {
        Name = newName;
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
    public void load(Scanner infile){

        Name = infile.next();
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
    /**
     * Reads in information about the cat from the file
     */


    public void save(PrintWriter pw){
        pw.println(Name);
        pw.println(originalOwners.size());
        for (Owner o : originalOwners) {
            pw.println(o.getName());
            pw.println(o.getPhone());
        }
        pw.println(canShare);
        pw.println(foodPerDay);
        pw.println(favFood);
    }


}



