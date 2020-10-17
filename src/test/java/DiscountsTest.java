import org.junit.Before;
import org.junit.Test;
import products.Order;
import util.Constants;
import util.Util;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DiscountsTest {

    private Order order;

    @Before
    public void init(){
        order = new Order();
    }

    @Test
    public void testBreadDiscount1(){
        Util.addBreads(order, 0, 2);
        Util.addBreads(order, 2, 1);
        Util.addBreads(order, 6, 3);
        Util.addBreads(order, 3, 1);
        Util.addBreads(order, 4, 1);
        Util.addBreads(order, 5, 1);
        Util.addBreads(order, 6, 2);

        assertEquals(new BigDecimal("22.00").compareTo(order.getOrderTotal()), 0);
        assertEquals(new BigDecimal("8.00").compareTo(order.getOrderDiscountsTotal()), 0);
    }


    @Test
    public void testBreadDiscount2(){
        Util.addBreads(order, 0, 1);
        Util.addBreads(order, 1, 2);
        Util.addBreads(order, 2, 3);

        assertNull(order.getAppliedDiscounts().get(Constants.ProductCode.BREAD_A));
        assertEquals(new BigDecimal("12.00").compareTo(order.getOrderTotal()), 0);
    }

    @Test
    public void testBeerDiscount1(){
        Util.addBeers(order, Constants.ProductCode.BEER_DUTCH, 5);
        Util.addBeers(order, Constants.ProductCode.BEER_BELGIUM, 9);

        assertEquals(new BigDecimal("3.00").compareTo(order.getOrderDiscountsTotal()), 0);
        assertEquals(new BigDecimal("25.50").compareTo(order.getOrderTotal()), 0);
    }

    @Test
    public void testBeerDiscount2(){
        Util.addBeers(order, Constants.ProductCode.BEER_DUTCH, 5);
        Util.addBeers(order, Constants.ProductCode.BEER_BELGIUM, 5);
        Util.addBeers(order, Constants.ProductCode.BEER_GERMAN, 5);

        assertNull(order.getAppliedDiscounts().get(Constants.ProductCode.BEER_DUTCH));
        assertNull(order.getAppliedDiscounts().get(Constants.ProductCode.BEER_BELGIUM));
        assertNull(order.getAppliedDiscounts().get(Constants.ProductCode.BEER_GERMAN));
        assertEquals(new BigDecimal("22.50").compareTo( order.getOrderTotal()), 0);
    }

    @Test
    public void testBeerDiscount3(){
        Util.addBeers(order, Constants.ProductCode.BEER_DUTCH, 6);
        Util.addBeers(order, Constants.ProductCode.BEER_BELGIUM, 9);
        Util.addBeers(order, Constants.ProductCode.BEER_GERMAN, 19);

        assertEquals(new BigDecimal("8.00").compareTo(order.getOrderDiscountsTotal()), 0);
        assertEquals(new BigDecimal("46.00").compareTo(order.getOrderTotal()), 0);
    }

    @Test
    public void testTotalDiscount1(){
        Util.addBeers(order, Constants.ProductCode.BEER_DUTCH, 7);
        Util.addBeers(order, Constants.ProductCode.BEER_BELGIUM, 5);
        Util.addBeers(order, Constants.ProductCode.BEER_GERMAN, 18);

        Util.addBreads(order, 1, 5);
        Util.addBreads(order, 3, 5);
        Util.addBreads(order, 4, 5);
        Util.addBreads(order, 6, 10);

        assertEquals(new BigDecimal("3.00").compareTo( order.getAppliedDiscounts().get(Constants.ProductCode.BEER_GERMAN)), 0);
        assertEquals(new BigDecimal("2.00").compareTo(order.getAppliedDiscounts().get(Constants.ProductCode.BEER_DUTCH)), 0);
        assertEquals(new BigDecimal("10.00").compareTo(order.getAppliedDiscounts().get(Constants.ProductCode.BREAD_B)), 0);
        assertEquals(new BigDecimal("12.00").compareTo(order.getAppliedDiscounts().get(Constants.ProductCode.BREAD_C)), 0);
        assertEquals(new BigDecimal("27.00").compareTo( order.getOrderDiscountsTotal()), 0);
        assertEquals(new BigDecimal("88.50").compareTo(order.getOrderTotal()), 0);
    }
}
