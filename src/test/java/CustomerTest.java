import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class CustomerTest {

	private Movie[] availableRentals;
  private Customer customer;

  @Before
  public void setUp() {

  	availableRentals = new Movie[] {
  		new Movie("The Terminator", Movie.REGULAR),
  		new Movie("Love Actually", Movie.REGULAR),
  		new Movie("The Pianist", Movie.REGULAR),
  		new Movie("Finding Nemo", Movie.CHILDRENS),
  		new Movie("Ice Age Collision COiurse", Movie.CHILDRENS),
  		new Movie("La La Land", Movie.NEW_RELEASE),
  		new Movie("Underworld: Blood Wars", Movie.NEW_RELEASE)
  	};
  	customer = new Customer("Joe Blogs");
  	
  }

  @Test
  public void statementMustHaveTheCustomerName() {

    // tested method call
    String statement = customer.statement();
    // assertion
    assertThat(statement, containsString(customer.getName()));

  }

  @Test
  public void statementMustHaveTheExpectedMovies() {

    // condition
    customer.addRental(new Rental(availableRentals[3], 10));
    customer.addRental(new Rental(availableRentals[6], 10));
    // tested method call
    String statement = customer.statement();
    // assertion
    assertThat(statement, 
      both(containsString(availableRentals[3].getTitle()))
      .and(containsString(availableRentals[6].getTitle())));

  }

  @Test
  public void statementMustHaveTheCorrectPriceForARegularMovie() {

    // condition
    customer.addRental(new Rental(availableRentals[0], 5));
    // tested method call
    String statement = customer.statement();
    // assertion
    assertThat(statement, containsString("Amount owed is 6.5"));

  }

  @Test
  public void statementMustHaveTheCorrectPriceForAChildrenMovie() {

    // condition
    customer.addRental(new Rental(availableRentals[3], 7));
    // tested method call
    String statement = customer.statement();
    // assertion
    assertThat(statement, containsString("Amount owed is 7.5"));

  }

  @Test
  public void statementMustHaveTheCorrectPriceForANewRelease() {

    // condition
    customer.addRental(new Rental(availableRentals[6], 10));
    // tested method call
    String statement = customer.statement();
    // assertion
    System.out.println(statement);
    assertThat(statement, containsString("Amount owed is 30"));

  }

  @Test
  public void statementMustHaveTheCorrectFrequentRenterPointsForARegularMovie() {

    // condition
    customer.addRental(new Rental(availableRentals[0], 5));
    // tested method call
    String statement = customer.statement();
    // assertion
    System.out.println(statement);
    assertThat(statement, containsString("You earned 1 frequent renter points"));

  }

  @Test
  public void statementMustHaveTheCorrectFrequentRenterPointsForAChildrenMovie() {

    // condition
    customer.addRental(new Rental(availableRentals[3], 7));
    // tested method call
    String statement = customer.statement();
    // assertion
    System.out.println(statement);
    assertThat(statement, containsString("You earned 1 frequent renter points"));

  }

  @Test
  public void statementMustHaveTheCorrectFrequentRenterPointsForANewRelease() {

    // condition
    customer.addRental(new Rental(availableRentals[6], 10));
    // tested method call
    String statement = customer.statement();
    // assertion
    System.out.println(statement);
    assertThat(statement, containsString("You earned 2 frequent renter points"));

  }

  @After
  public void tearDown() {
  	customer = null;
  }


}