import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Dog extends Animal{
    protected Boolean likesBones;
     boolean needsWalk;
    private ArrayList<String> kindsOfFoodEaten;
    public Dog(String name, String food,
               int mealsPerDay){
        Name = name;
        originalOwners = new ArrayList<Owner>();
        this.favFood = food;
        this.foodPerDay = mealsPerDay;
        setAnimal("Dog");
        likesBones = true;
    }
    public void loadDogMethods(){
        try (FileReader fr = new FileReader("dog.txt");
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            likesBones = infile.nextBoolean();
        }
        catch (Exception e){

        }

    }

}
