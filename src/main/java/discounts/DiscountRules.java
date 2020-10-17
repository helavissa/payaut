package discounts;

import products.ShoppingItem;

public class DiscountRules {

    private static final NoDiscountPolicy NO_DISCOUNT_POLICY = new NoDiscountPolicy();
    private static final TwoForOnePolicy TWO_FOR_ONE_POLICY = new TwoForOnePolicy();
    private static final ThreeForOnePolicy THREE_FOR_ONE_POLICY = new ThreeForOnePolicy();
    private static final MinusThreeForPackPolicy MINUS_THREE_FOR_PACK_POLICY = new MinusThreeForPackPolicy();
    private static final MinusTwoForPackPolicy MINUS_TWO_FOR_PACK_POLICY = new MinusTwoForPackPolicy();
    private static final MinusOneForPackPolicy MINUS_ONE_FOR_PACK_POLICY = new MinusOneForPackPolicy();

    public static DiscountPolicy getDiscountPolicy(ShoppingItem shoppingItem){
        switch (shoppingItem.getProductCode()) {
            case BREAD_A:
                return NO_DISCOUNT_POLICY;
            case BREAD_B:
                return TWO_FOR_ONE_POLICY;
            case BREAD_C:
                return THREE_FOR_ONE_POLICY;
            case BEER_BELGIUM:
                return MINUS_THREE_FOR_PACK_POLICY;
            case BEER_DUTCH:
                return MINUS_TWO_FOR_PACK_POLICY;
            case BEER_GERMAN:
                return MINUS_ONE_FOR_PACK_POLICY;
            default:
                throw new IllegalArgumentException("Unknown type of product: " + shoppingItem.getProductCode());
        }
    }
}
