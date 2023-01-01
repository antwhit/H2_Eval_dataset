class Casts {

    class Vehicle {

        public static final String brand = "Ferrari";
    }

    class Car extends Vehicle {

        Car c = new Car();

        Vehicle v = (Vehicle) c;

        Object o = (Object) c;
    }

    class Test {

        int i = 5;

        int j = 6;

        float f = (float) i;

        float g = (float) i / (float) j;
    }
}
