import org.junit.Before;
import org.junit.Test;
import products.Beer;
import products.Bread;
import products.Order;
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

        order.applyDiscounts();
        assertEquals(new BigDecimal("8.00"), order.getTotalDiscounts());
    }


    @Test
    public void testBreadDiscount2(){
        Util.addBreads(order, 0, 1);
        Util.addBreads(order, 1, 2);
        Util.addBreads(order, 2, 3);

        order.applyDiscounts();
        assertNull(order.getAppliedDiscounts().get(Bread.name));
    }

    @Test
    public void testBeerDiscount1(){
        Util.addBeers(order, Beer.BeerType.DUTCH, 5);
        Util.addBeers(order, Beer.BeerType.BELGIUM, 9);

        order.applyDiscounts();
        assertEquals(new BigDecimal("3.00"), order.getTotalDiscounts());
    }

    @Test
    public void testBeerDiscount2(){
        Util.addBeers(order, Beer.BeerType.DUTCH, 5);
        Util.addBeers(order, Beer.BeerType.BELGIUM, 5);
        Util.addBeers(order, Beer.BeerType.GERMAN, 5);

        order.applyDiscounts();
        assertNull(order.getAppliedDiscounts().get(Beer.BeerType.DUTCH.toString()));
        assertNull(order.getAppliedDiscounts().get(Beer.BeerType.BELGIUM.toString()));
        assertNull(order.getAppliedDiscounts().get(Beer.BeerType.GERMAN.toString()));
        assertEquals(BigDecimal.ZERO, order.getTotalDiscounts());
    }

    @Test
    public void testTotalDiscount1(){
        Util.addBeers(order, Beer.BeerType.DUTCH, 7);
        Util.addBeers(order, Beer.BeerType.BELGIUM, 5);
        Util.addBeers(order, Beer.BeerType.GERMAN, 18);

        Util.addBreads(order, 1, 5);
        Util.addBreads(order, 3, 5);
        Util.addBreads(order, 4, 5);
        Util.addBreads(order, 6, 10);

        order.applyDiscounts();

        assertEquals(new BigDecimal("3.00"), order.getAppliedDiscounts().get(Beer.BeerType.GERMAN.toString()));
        assertEquals(new BigDecimal("2.00"), order.getAppliedDiscounts().get(Beer.BeerType.DUTCH.toString()));
        assertEquals(new BigDecimal("22.00"), order.getAppliedDiscounts().get(Bread.name));
        assertEquals(new BigDecimal("27.00"), order.getTotalDiscounts());
        assertEquals(new BigDecimal("61.50"), order.getTotal());
    }
}
