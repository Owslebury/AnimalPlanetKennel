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
        likesBones = true;
        sharesRuns = false;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dog{name=").append(Name)
                .append(", Original Owner(s) with phone: ").append(originalOwners)
                .append(", Food per day: ").append(" times");
        return sb.toString();
    }


}
