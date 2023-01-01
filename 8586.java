public class HouseDirector {

    public void CreateHouse(HouseBuilder concreteBuilder) {
        concreteBuilder.BuildRoom(1);
        concreteBuilder.BuildRoom(2);
        concreteBuilder.BuildDoor(1, 2);
    }
}
