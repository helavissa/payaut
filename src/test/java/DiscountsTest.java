import discounts.OrderState;
import org.junit.Before;
import org.junit.Test;
import products.Beer;
import products.Bread;
import products.ShoppingItem;
import util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DiscountsTest {

    private Map<String, BigDecimal> appliedDiscounts;
    private OrderState orderState;
    private List<ShoppingItem> shoppingItems;

    @Before
    public void init(){
        appliedDiscounts = new HashMap<>();
        orderState = new OrderState();
        shoppingItems = new ArrayList<>();
    }

    @Test
    public void checkAddDiscount(){
        Util.addDiscount(appliedDiscounts, "bread", new BigDecimal("2.00"));
        Util.addDiscount(appliedDiscounts, "bread", new BigDecimal("3.00"));
        Util.addDiscount(appliedDiscounts, "beer", new BigDecimal("1.00"));

        assertEquals(appliedDiscounts.get("bread"), new BigDecimal("5.00"));
        assertEquals(appliedDiscounts.get("beer"), new BigDecimal("1.00"));
    }

    @Test
    public void testBreadDiscount1(){
        Util.addBreads(shoppingItems, 0, 2);
        Util.addBreads(shoppingItems, 2, 1);
        Util.addBreads(shoppingItems, 6, 3);
        Util.addBreads(shoppingItems, 3, 1);
        Util.addBreads(shoppingItems, 4, 1);
        Util.addBreads(shoppingItems, 5, 1);
        Util.addBreads(shoppingItems, 6, 2);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, orderState);
        assertEquals(new BigDecimal("8.00"), appliedDiscounts.get(Bread.name));
    }


    @Test
    public void testBreadDiscount2(){
        Util.addBreads(shoppingItems, 0, 1);
        Util.addBreads(shoppingItems, 1, 2);
        Util.addBreads(shoppingItems, 2, 3);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, orderState);
        assertNull(appliedDiscounts.get(Bread.name));
    }

    @Test
    public void testBeerDiscount1(){
        Util.addBeers(shoppingItems, Beer.BeerType.DUTCH, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.BELGIUM, 9);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, orderState);
        assertEquals(new BigDecimal("3.00"), appliedDiscounts.get(Beer.BeerType.BELGIUM.toString()));
    }

    @Test
    public void testBeerDiscount2(){
        Util.addBeers(shoppingItems, Beer.BeerType.DUTCH, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.BELGIUM, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.GERMAN, 5);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, orderState);
        assertNull(appliedDiscounts.get(Beer.BeerType.BELGIUM.toString()));
    }

    @Test
    public void testTotalDiscount1(){
        Util.addBeers(shoppingItems, Beer.BeerType.DUTCH, 7);
        Util.addBeers(shoppingItems, Beer.BeerType.BELGIUM, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.GERMAN, 18);
        Util.addBreads(shoppingItems, 1, 5);
        Util.addBreads(shoppingItems, 3, 5);
        Util.addBreads(shoppingItems, 4, 5);
        Util.addBreads(shoppingItems, 6, 10);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, orderState);
        Util.printDiscounts(appliedDiscounts);

        assertEquals(new BigDecimal("3.00"), appliedDiscounts.get(Beer.BeerType.GERMAN.toString()));
        assertEquals(new BigDecimal("2.00"), appliedDiscounts.get(Beer.BeerType.DUTCH.toString()));
        assertEquals(new BigDecimal("22.00"), appliedDiscounts.get(Bread.name));
    }
}
