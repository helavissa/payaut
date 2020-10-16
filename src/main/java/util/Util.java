package util;

import discounts.Discounts;
import products.Beer;
import products.ShoppingItem;

import java.math.BigDecimal;
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

    public static void applyDiscounts(List<ShoppingItem> shoppingList, Map<String, BigDecimal> appliedDiscounts, Discounts discounts){
        for(ShoppingItem item: shoppingList){
            BigDecimal discountAmount = item.getDiscountPolicy().getDiscount(item, discounts);
            if(discountAmount.compareTo(BigDecimal.ZERO) > 0){
                Util.addDiscount(appliedDiscounts, item.getProductName(), discountAmount);
            }
        }
    }

    public static void printDiscounts(Map<String, BigDecimal> discounts){
        discounts.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public static void addBeers(List<ShoppingItem> shoppingItems, Beer.BeerType beerType, int amount){
        for(int i = 0; i < amount; i++){
            shoppingItems.add(new Beer(beerType));
        }
    }
}
