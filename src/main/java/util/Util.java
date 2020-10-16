package util;

import discounts.OrderState;
import products.Beer;
import products.Bread;
import products.ShoppingItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static void addDiscount(Map<String, BigDecimal> discounts, String productName, BigDecimal productDiscount){
        // TODO: check for null?
        BigDecimal discount = discounts.get(productName);
        if(discount == null){
            discounts.put(productName, productDiscount);
        }else{
            discounts.put(productName, discount.add(productDiscount));
        }
    }

    public static void applyDiscounts(List<ShoppingItem> shoppingList, Map<String, BigDecimal> appliedDiscounts, OrderState orderState){
        for(ShoppingItem item: shoppingList){
            BigDecimal discountAmount = item.getDiscountPolicy().getDiscount(item, orderState);
            if(discountAmount.compareTo(BigDecimal.ZERO) > 0){
                Util.addDiscount(appliedDiscounts, item.getProductName(), discountAmount);
            }
        }
    }

    public static void printReceipt(List<ShoppingItem> list){
        Map<String, BigDecimal> appliedDiscounts = new HashMap<>();
        Util.applyDiscounts(list, appliedDiscounts, new OrderState());

        BigDecimal totalBeforeDiscounts = list
                .stream()
                .map(ShoppingItem::getPrice)
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        BigDecimal totalDiscounts = appliedDiscounts.values().stream().reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        String format = "%-30s%15s";
        System.out.println(String.format("%45s", "").replace(' ', '*'));
        System.out.println(String.format(format,  "Item", "Amount"));
        list.forEach(shoppingItem -> System.out.println(String.format(format,  shoppingItem.getProductName(), shoppingItem.getPrice())));

        System.out.println(String.format("%45s", "").replace(' ', '*'));
        System.out.println(String.format(format,  "Total before discounts:", totalBeforeDiscounts));
        System.out.println(String.format("%45s", "").replace(' ', '*'));

        System.out.println("Discounts: ");
        System.out.println(String.format(format,  "Item", "Amount"));
        appliedDiscounts.forEach((key, value) ->
                System.out.println(String.format(format,  key, value)));
        System.out.println(String.format("%45s", "").replace(' ', '*'));
        System.out.println(String.format(format,  "Total discounts:", totalDiscounts));
        System.out.println(String.format(format,  "TOTAL:", totalBeforeDiscounts.subtract(totalDiscounts)));
    }

    public static void printDiscounts(Map<String, BigDecimal> discounts){
        discounts.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public static void addBeers(List<ShoppingItem> shoppingItems, Beer.BeerType beerType, int amount){
        for(int i = 0; i < amount; i++){
            shoppingItems.add(new Beer(beerType));
        }
    }

    public static void addBreads(List<ShoppingItem> shoppingItems, int daysOld, int amount){
        for(int i = 0; i < amount; i++){
            shoppingItems.add(new Bread(LocalDate.now().minusDays(daysOld)));
        }
    }
}
