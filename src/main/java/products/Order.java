package products;

import discounts.DiscountRules;
import products.bread.Bread;
import util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private final Map<ShoppingItem, Integer> shoppingList;
    private final Map<Constants.ProductCode, BigDecimal> appliedDiscounts;
    private BigDecimal orderTotal;
    private BigDecimal orderDiscountsTotal;

    public Order() {
        shoppingList = new HashMap<>();
        appliedDiscounts = new HashMap<>();
        orderTotal = BigDecimal.ZERO;
        orderDiscountsTotal = BigDecimal.ZERO;
    }

    public Map<Constants.ProductCode, BigDecimal> getAppliedDiscounts() {
        return appliedDiscounts;
    }

    public void addItem(ShoppingItem shoppingItem){
        if(shoppingItem instanceof Bread && ((Bread) shoppingItem).isTooOld()){
            throw new IllegalArgumentException("Cannot add old bread to order");
        }
        Integer amountOfProduct = shoppingList.get(shoppingItem);
        amountOfProduct = (amountOfProduct == null) ? 1 : ++amountOfProduct;

        orderTotal = orderTotal.add(shoppingItem.getPrice());
        BigDecimal discountAmount = DiscountRules.getDiscountPolicy(shoppingItem).getDiscount(amountOfProduct, shoppingItem);
        if(discountAmount.compareTo(BigDecimal.ZERO) > 0){
            addDiscount(shoppingItem, discountAmount);
            orderDiscountsTotal = orderDiscountsTotal.add(discountAmount).setScale(2, RoundingMode.HALF_UP);
        }
        shoppingList.put(shoppingItem, amountOfProduct);
    }

    public void addDiscount(ShoppingItem shoppingItem, BigDecimal productDiscount){
        BigDecimal discount = appliedDiscounts.getOrDefault(shoppingItem.getProductCode(), BigDecimal.ZERO);
        appliedDiscounts.put(shoppingItem.getProductCode(), discount.add(productDiscount).setScale(2, RoundingMode.HALF_UP));
    }

    public void printReceipt(){
        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Item", "Amount"));
        shoppingList.forEach((key, value) ->
                System.out.println(String.format(Constants.RECEIPT_FORMAT,
                        (key.getProductCode() + " x " + value),
                        key.getPrice().multiply(new BigDecimal(value)).setScale(2, RoundingMode.HALF_UP))));


        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Total before discounts:", orderTotal));
        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));

        System.out.println("Discounts: ");
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Item", "Amount"));
        appliedDiscounts.forEach((key, value) ->
                System.out.println(String.format(Constants.RECEIPT_FORMAT,
                        key, value)));
        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Total discounts:", orderDiscountsTotal));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "TOTAL:", orderTotal.subtract(orderDiscountsTotal)));
    }

    public BigDecimal getOrderTotal(){
        return orderTotal;
    }

    public BigDecimal getOrderDiscountsTotal(){
        return orderDiscountsTotal;
    }

}
