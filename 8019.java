public class Cat extends Animal {

    private String name;

    protected String breed;

    public Cat(String aName) {
        super("Cat");
        name = aName;
    }

    public Cat(String aName, String aBreed) {
        super("Cat");
        name = aName;
        breed = aBreed;
    }

    public void describe() {
        System.out.println(name + ", a breed of " + type + " called " + breed);
    }

    public void sound() {
        System.out.println("Meow");
    }

    public void sleep() {
        System.out.println(name + " is having purrfect dreams!");
    }

    public void move() {
        System.out.println("This little kitty moves fast!");
    }

    public String getName() {
        return name;
    }
}
