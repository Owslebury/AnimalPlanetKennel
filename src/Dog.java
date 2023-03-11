import java.util.ArrayList;

public class Dog extends Animal{
     boolean needsWalk;
    private ArrayList<String> kindsOfFoodEaten;
    public Dog(String name, boolean share, String food,
               int mealsPerDay){
        Name = name;
        originalOwners = new ArrayList<Owner>();
        this.canShare = share;
        this.favFood = food;
        this.foodPerDay = mealsPerDay;
        setAnimal("Dog");
        likesBones = true;
        sharesRuns = false;
    }

}
