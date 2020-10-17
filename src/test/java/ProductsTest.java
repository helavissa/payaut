import org.junit.Test;
import products.beer.BelgiumBeer;
import products.beer.DutchBeer;
import products.beer.GermanBeer;
import products.bread.Bread;
import util.Constants;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ProductsTest {

    @Test
    public void testBread(){
        Bread breadNew = new Bread(LocalDate.now()); // test new bread

        assertTrue(breadNew.isTwoDaysOldOrLess());
        assertFalse(breadNew.isBetweenThreeAndFiveDaysOld());
        assertFalse(breadNew.isSixDaysOld());
        assertFalse(breadNew.isTooOld());

        Bread bread3 = new Bread(LocalDate.now().minusDays(3)); // test 3 days bread
        assertFalse(bread3.isTwoDaysOldOrLess());
        assertTrue(bread3.isBetweenThreeAndFiveDaysOld());
        assertFalse(bread3.isSixDaysOld());
        assertFalse(bread3.isTooOld());

        Bread bread5 = new Bread(LocalDate.now().minusDays(5)); // test 5 days bread

        assertFalse(bread5.isTwoDaysOldOrLess());
        assertTrue(bread5.isBetweenThreeAndFiveDaysOld());
        assertFalse(bread5.isSixDaysOld());
        assertFalse(bread5.isTooOld());

        Bread bread6 = new Bread(LocalDate.now().minusDays(6)); // test 6 days bread

        assertFalse(bread6.isTwoDaysOldOrLess());
        assertFalse(bread6.isBetweenThreeAndFiveDaysOld());
        assertTrue(bread6.isSixDaysOld());
        assertFalse(bread6.isTooOld());

        Bread breadOld = new Bread(LocalDate.now().minusDays(7)); // test old bread

        assertFalse(breadOld.isTwoDaysOldOrLess());
        assertFalse(breadOld.isBetweenThreeAndFiveDaysOld());
        assertFalse(breadOld.isSixDaysOld());
        assertTrue(breadOld.isTooOld());
    }

    @Test
    public void testBeer(){
        BelgiumBeer belgiumBeer = new BelgiumBeer();
        assertEquals(Constants.BELGIUM_BEER_COST.compareTo(belgiumBeer.getPrice()), 0);

        DutchBeer dutchBeer = new DutchBeer();
        assertEquals(Constants.DUTCH_BEER_COST.compareTo(dutchBeer.getPrice()), 0);

        GermanBeer germanBeer = new GermanBeer();
        assertEquals(Constants.GERMAN_BEER_COST.compareTo(germanBeer.getPrice()), 0);
    }

}
