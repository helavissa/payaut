import discounts.BreadDiscount;
import org.junit.Test;
import products.Bread;
import util.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class DiscountsTest {

    @Test
    public void checkAddDiscount(){
        Map<String, BigDecimal> discounts = new HashMap<>();
        Util.addDiscount(discounts, "bread", new BigDecimal("2.00"));
        Util.addDiscount(discounts, "bread", new BigDecimal("3.00"));
        Util.addDiscount(discounts, "beer", new BigDecimal("1.00"));

        assertEquals(discounts.get("bread"), new BigDecimal("5.00"));
        assertEquals(discounts.get("beer"), new BigDecimal("1.00"));
    }
    @Test
    public void testBreadDiscount(){
        Map<String, BigDecimal> discounts = new HashMap<>();
        List<Bread> breadList = new ArrayList<>();

        Bread bread1 = new Bread(LocalDate.now());
        Bread bread2 = new Bread(LocalDate.now());
        Bread bread3 = new Bread(LocalDate.now().minusDays(2));

        Bread bread4 = new Bread(LocalDate.now().minusDays(3));
        Bread bread5 = new Bread(LocalDate.now().minusDays(5));
        Bread bread6 = new Bread(LocalDate.now().minusDays(4));

        Bread bread7 = new Bread(LocalDate.now().minusDays(6));
        Bread bread8 = new Bread(LocalDate.now().minusDays(6));
        Bread bread9 = new Bread(LocalDate.now().minusDays(6));
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

        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.applyDiscounts(discounts, breadList);
        Util.printDiscounts(discounts);
    }
}
