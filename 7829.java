import fit.RowFixture;

public class PersonVerificationRowFixture extends RowFixture {

    @Override
    public Class getTargetClass() {
        return Person.class;
    }

    @Override
    public Object[] query() throws Exception {
        return new Person[] { new Person("Fred", 12), new Person("Jim", 40) };
    }

    public class Person {

        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String name() {
            return name;
        }

        public int age() {
            return age;
        }
    }
}
