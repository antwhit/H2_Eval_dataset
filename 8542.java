import org.nakedobjects.example.library.Book;
import org.nakedobjects.example.library.Loan;
import org.nakedobjects.example.library.Member;
import org.nakedobjects.example.xat.JavaAcceptanceTestCase;
import org.nakedobjects.reflector.java.fixture.JavaFixture;

public class LibraryTest extends JavaAcceptanceTestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(LibraryTest.class);
    }

    public LibraryTest(String name) {
        super(name);
    }

    public void testEverything() {
        title("Stock library with books");
        nextStep();
    }

    protected void setUpFixtures() {
        addFixture(new JavaFixture() {

            public void install() {
                registerClass(Member.class);
                registerClass(Book.class);
                registerClass(Loan.class);
            }
        });
    }
}
