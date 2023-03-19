import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dog extends Animal{
    protected Boolean likesBones;
    protected Boolean needsWalk;
    protected String DietInfo;
    private Animal baseAnimal;
    public Dog(Animal base){
        baseAnimal = base;
        originalOwners = new ArrayList<Owner>();
        setAnimal("Dog");
        loadDogMethods(getName());
    }
    public Dog loadDogMethods(String dogName){
        try (FileReader fr = new FileReader("dog.txt");
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {
            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");
            while (infile.hasNextLine()) {
                String tempName = infile.nextLine();
                likesBones = infile.nextBoolean();
                needsWalk = infile.nextBoolean();
                String dietInfo = infile.nextLine().trim();
                if (tempName.equals(dogName)) {
                    infile.close();
                    return this;
                }
            }
            System.out.println("Dog not found!");
            return null;
        }
        catch (Exception e){
            System.out.println("Error searching for dog");
            return null;
        }

    }

    public void saveDog(){
        try{
            //filewriter appends to the end of the file so multiple dogs can be added.
            FileWriter writer = new FileWriter("dog.txt", true);
            writer.write(baseAnimal.getName() + "\n");
            writer.write(likesBones + "\n");
            writer.write(needsWalk + "\n");
            writer.write(DietInfo + "\n");
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
