package products;

import discounts.OrderState;
import util.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bread implements ShoppingItem {

    public static final String name = "Bread";
    private LocalDate dateProduced;

    public Bread(LocalDate dateProduced) {
        this.dateProduced = dateProduced;
    }

    private LocalDate getDateProduced() {
        return dateProduced;
    }

    public void setDateProduced(LocalDate dateProduced) {
        this.dateProduced = dateProduced;
    }

    public String getProductName(){
        return name;
    }

    public boolean isTwoDaysOldOrLess(){
        return DAYS.between(getDateProduced(), LocalDate.now()) <= 2;
    }

    public boolean isBetweenThreeAndFiveDaysOld(){
        return DAYS.between(getDateProduced(), LocalDate.now()) >= 3 &&
                DAYS.between(getDateProduced(), LocalDate.now()) <= 5;
    }

    public boolean isSixDaysOld(){
        return DAYS.between(getDateProduced(), LocalDate.now()) == 6;
    }

    public boolean isTooOld(){
        return DAYS.between(getDateProduced(), LocalDate.now()) > 6;
    }

    @Override
    public BigDecimal getPrice() {
        return Constants.BREAD_PRICE;
    }

    @Override
    public BigDecimal getDiscount(OrderState orderState) {
        if(this.isTwoDaysOldOrLess()){
            return BigDecimal.ZERO;
        }

        // discount breads if applicable, or increase counter for free breads, depending on type
        if(this.isBetweenThreeAndFiveDaysOld()){
            if(orderState.getAmountFreeBreadsB() > 0){
                orderState.setAmountFreeBreadsB(orderState.getAmountFreeBreadsB() - 1);
                return this.getPrice();
            }else{
                orderState.setAmountFreeBreadsB(orderState.getAmountFreeBreadsB() + 1);
            }
        }else if(this.isSixDaysOld()){
            if(orderState.getAmountFreeBreadsC() > 0){
                orderState.setAmountFreeBreadsC(orderState.getAmountFreeBreadsC() - 1);
                return this.getPrice();
            }else{
                orderState.setAmountFreeBreadsC(orderState.getAmountFreeBreadsC() + 2);
            }
        }
        return BigDecimal.ZERO;
    }
}

