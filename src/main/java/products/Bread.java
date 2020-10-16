package products;

import discounts.DiscountPolicies;
import discounts.DiscountPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bread implements ShoppingItem{

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
        return new BigDecimal("2.00");
    }

    @Override
    public DiscountPolicy getDiscountPolicy() {
        return DiscountPolicies.breadDiscountPolicy;
    }
}

