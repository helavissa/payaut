package products.bread;

import products.ShoppingItem;
import util.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bread implements ShoppingItem {

    private final LocalDate dateProduced;

    public Bread(LocalDate dateProduced) {
        this.dateProduced = dateProduced;
    }

    private LocalDate getDateProduced() {
        return dateProduced;
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
    public Constants.ProductCode getProductCode(){
        if(isTwoDaysOldOrLess()){
            return Constants.ProductCode.BREAD_A;
        }else if(isBetweenThreeAndFiveDaysOld()){
            return Constants.ProductCode.BREAD_B;
        }else if(isSixDaysOld()){
            return Constants.ProductCode.BREAD_C;
        }
        return Constants.ProductCode.BREAD_OLD;
    }

    @Override
    public BigDecimal getPrice() {
        return Constants.BREAD_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bread bread = (Bread) o;
        return getProductCode().equals(bread.getProductCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCode());
    }
}

