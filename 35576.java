public class Person {

    public static enum Gender {

        MALE, FEMALE
    }

    ;

    private int age;

    private Gender gender;

    private String churchname;

    private String fullname;

    public Person() {
    }

    public Person(String fullname) {
        this.fullname = fullname;
    }

    void eat() {
    }

    ;

    void sleep() {
    }

    ;

    void play() {
    }

    ;

    void learn() {
    }

    ;

    void worship() {
    }

    ;

    void memorize() {
    }

    ;

    void horseplay() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getChurchname() {
        return churchname;
    }

    public void setChurchname(String churchname) {
        this.churchname = churchname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    ;
}
