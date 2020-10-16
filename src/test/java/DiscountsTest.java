import discounts.Discounts;
import org.junit.Before;
import org.junit.Test;
import products.Beer;
import products.Bread;
import products.ShoppingItem;
import util.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DiscountsTest {

    private Map<String, BigDecimal> appliedDiscounts;
    private Discounts discounts;

    @Before
    public void init(){
        appliedDiscounts = new HashMap<>();
        discounts = new Discounts();
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
        List<ShoppingItem> breadList = new ArrayList<>();

        Bread bread1 = new Bread(LocalDate.now());
        Bread bread2 = new Bread(LocalDate.now());
        Bread bread3 = new Bread(LocalDate.now().minusDays(2));

        Bread bread4 = new Bread(LocalDate.now().minusDays(6));
        Bread bread5 = new Bread(LocalDate.now().minusDays(6));
        Bread bread6 = new Bread(LocalDate.now().minusDays(6));

        Bread bread7 = new Bread(LocalDate.now().minusDays(3));
        Bread bread8 = new Bread(LocalDate.now().minusDays(4));
        Bread bread9 = new Bread(LocalDate.now().minusDays(5));
        Bread bread10 = new Bread(LocalDate.now().minusDays(6));
        Bread bread11 = new Bread(LocalDate.now().minusDays(6));

        breadList.add(bread1);
        breadList.add(bread2);
        breadList.add(bread3);
        breadList.add(bread4);
        breadList.add(bread5);
        breadList.add(bread6);
        breadList.add(bread7);
        breadList.add(bread8);
        breadList.add(bread9);
        breadList.add(bread10);
        breadList.add(bread11);

        Util.applyDiscounts(breadList, appliedDiscounts, discounts);
        assertEquals(new BigDecimal("8.00"), appliedDiscounts.get(Bread.name));
    }


    @Test
    public void testBreadDiscount2(){
        List<ShoppingItem> breadList = new ArrayList<>();

        Bread bread1 = new Bread(LocalDate.now());
        Bread bread2 = new Bread(LocalDate.now());

        breadList.add(bread1);
        breadList.add(bread2);

        Util.applyDiscounts(breadList, appliedDiscounts, discounts);
        assertNull(appliedDiscounts.get(Bread.name));
    }

    @Test
    public void testBeerDiscount1(){
        List<ShoppingItem> shoppingItems = new ArrayList<>();

        Util.addBeers(shoppingItems, Beer.BeerType.DUTCH, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.BELGIUM, 9);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, discounts);
        assertEquals(new BigDecimal("3.00"), appliedDiscounts.get(Beer.BeerType.BELGIUM.toString()));
    }

    @Test
    public void testBeerDiscount2(){
        List<ShoppingItem> shoppingItems = new ArrayList<>();

        Util.addBeers(shoppingItems, Beer.BeerType.DUTCH, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.BELGIUM, 5);
        Util.addBeers(shoppingItems, Beer.BeerType.GERMAN, 5);

        Util.applyDiscounts(shoppingItems, appliedDiscounts, discounts);
        assertNull(appliedDiscounts.get(Beer.BeerType.BELGIUM.toString()));
    }
}
