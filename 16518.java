import java.text.DecimalFormat;

public class TestCircle {

    public static void main(String[] args) {
        DecimalFormat showTwoDecimals = new DecimalFormat("0.00");
        Circle c1 = new Circle(2.5, 22, 44);
        double newRadius = 6.0;
        System.out.println("This shape is a " + c1.getName() + "\nlocated at " + c1.getLocation() + "\nRadius is " + c1.getRadius());
        System.out.println("The shape area is: " + showTwoDecimals.format(c1.area()) + "\n");
        System.out.println("The shape perimeter (circumference) is: " + showTwoDecimals.format(c1.perimeter()) + "\n");
        System.out.println("After changing the radius to " + newRadius);
        c1.setRadius(newRadius);
        System.out.println(", it is a" + c1 + "\nArea is " + showTwoDecimals.format(c1.area()) + "\n");
        System.out.println("The shape perimeter (circumference) is: " + showTwoDecimals.format(c1.perimeter()) + "\n");
    }
}
