class DogTestDrive {

    public static void main(String[] args) {
        int size = 7;
        Dog[] pets = new Dog[size];
        for (int i = 0; i < pets.length; ++i) {
            pets[i] = new Dog();
            pets[i].set_size(i * i * i);
            pets[i].set_name("Name " + i);
        }
        for (int i = 0; i < pets.length; ++i) {
            pets[i].bark();
        }
    }
}
