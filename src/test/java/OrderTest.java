import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import products.Order;
import products.bread.Bread;
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
        Bread bread = new Bread(LocalDate.now());
        Bread bread2 = new Bread(LocalDate.now().minusDays(5));
        order.addDiscount(bread, new BigDecimal("2.00"));
        order.addDiscount(bread, new BigDecimal("3.00"));
        order.addDiscount(bread2, new BigDecimal("1.00"));

        assertEquals(new BigDecimal("5.00").compareTo(order.getAppliedDiscounts().get(bread.getProductCode())), 0);
        assertEquals(new BigDecimal("1.00").compareTo(order.getAppliedDiscounts().get(bread2.getProductCode())), 0);
    }

    @Test
    public void checkPrint(){
        Util.addBreads(order, 3, 2);
        Util.addBreads(order, 6, 3);
        Util.addBeers(order, Constants.ProductCode.BEER_GERMAN, 6);
        order.printReceipt();
        assertTrue(outContent.toString().contains(String.format(Constants.RECEIPT_FORMAT,  "Total discounts:", "7.00")));
        assertTrue(outContent.toString().contains(String.format(Constants.RECEIPT_FORMAT,  "TOTAL:", "9.00")));
    }
}
