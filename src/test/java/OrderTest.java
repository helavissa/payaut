import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import products.Beer;
import products.Bread;
import products.Order;
import util.Constants;
import util.Util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderTest {

    private Order order;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init(){
        order = new Order();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStream() {
        System.setOut(System.out);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOldBreadException() {
        Bread breadOld = new Bread(LocalDate.now().minusDays(7));
        order.addItem(breadOld);
    }

    @Test
    public void checkAddDiscount(){
        order.addDiscount("bread", new BigDecimal("2.00"));
        order.addDiscount( "bread", new BigDecimal("3.00"));
        order.addDiscount( "beer", new BigDecimal("1.00"));

        assertEquals(order.getAppliedDiscounts().get("bread"), new BigDecimal("5.00"));
        assertEquals(order.getAppliedDiscounts().get("beer"), new BigDecimal("1.00"));
    }

    @Test
    public void checkPrint(){
        Util.addBreads(order, 3, 2);
        Util.addBreads(order, 6, 3);
        Util.addBeers(order, Beer.BeerType.GERMAN, 6);
        order.printReceipt();
        assertTrue(outContent.toString().contains(String.format(Constants.RECEIPT_FORMAT,  "Total discounts:", "7.00")));
        assertTrue(outContent.toString().contains(String.format(Constants.RECEIPT_FORMAT,  "TOTAL:", "9.00")));
    }
}
