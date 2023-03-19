import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Dog extends Animal{
    protected Boolean likesBones;
    protected Boolean needsWalk;
    protected ArrayList<String> DietInfo = new ArrayList<String>();
    public Dog(Animal base){
        originalOwners = new ArrayList<Owner>();
        setAnimal("Dog");
        loadDogMethods(getName());
    }
    public void loadDogMethods(String dogName){
        try (FileReader fr = new FileReader("dog.txt");
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            likesBones = infile.nextBoolean();
            needsWalk = infile.nextBoolean();
            while (infile.hasNext()){
                DietInfo.add(infile.nextLine());
            }
        }
        catch (Exception e){

        }

    }

}
