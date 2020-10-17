package products;

import discounts.OrderState;
import util.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private List<ShoppingItem> shoppingList;
    private Map<String, BigDecimal> appliedDiscounts;
    private OrderState orderState;
    private boolean discountsApplied;

    public Order() {
        shoppingList = new ArrayList<>();
        appliedDiscounts = new HashMap<>();
        orderState = new OrderState();
        discountsApplied = false;
    }

    public Map<String, BigDecimal> getAppliedDiscounts() {
        return appliedDiscounts;
    }

    public void setAppliedDiscounts(Map<String, BigDecimal> appliedDiscounts) {
        this.appliedDiscounts = appliedDiscounts;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void addItem(ShoppingItem shoppingItem){
        if(shoppingItem instanceof Bread && ((Bread) shoppingItem).isTooOld()){
            throw new IllegalArgumentException("Cannot add old bread to order");
        }
        shoppingList.add(shoppingItem);
    }

    public void addDiscount(String productName, BigDecimal productDiscount){
        BigDecimal discount = appliedDiscounts.get(productName);
        if(discount == null){
            appliedDiscounts.put(productName, productDiscount);
        }else{
            appliedDiscounts.put(productName, discount.add(productDiscount));
        }
    }

    public void applyDiscounts(){
        if(!discountsApplied){
            shoppingList.forEach(n -> {
                BigDecimal discountAmount = n.getDiscount(orderState);
                if(discountAmount.compareTo(BigDecimal.ZERO) > 0){
                    addDiscount(n.getProductName(), discountAmount);
                }
            });
        }
        this.discountsApplied = true;
    }

    public void printReceipt(){
        this.applyDiscounts();

        BigDecimal totalBeforeDiscounts = getTotalBeforeDiscounts();
        BigDecimal totalDiscounts = getTotalDiscounts();

        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Item", "Amount"));
        shoppingList.forEach(shoppingItem -> System.out.println(String.format(Constants.RECEIPT_FORMAT,  shoppingItem.getProductName(), shoppingItem.getPrice())));

        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Total before discounts:", totalBeforeDiscounts));
        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));

        System.out.println("Discounts: ");
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Item", "Amount"));
        appliedDiscounts.forEach((key, value) ->
                System.out.println(String.format(Constants.RECEIPT_FORMAT,  key, value)));
        System.out.println(String.format(Constants.RECEIPT_FORMAT_2, "").replace(' ', '*'));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "Total discounts:", totalDiscounts));
        System.out.println(String.format(Constants.RECEIPT_FORMAT,  "TOTAL:", getTotal()));
    }

    private BigDecimal getTotalBeforeDiscounts(){
        return shoppingList
                .stream()
                .map(ShoppingItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalDiscounts(){
        return appliedDiscounts.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal(){
        return getTotalBeforeDiscounts().subtract(getTotalDiscounts());
    }
}
