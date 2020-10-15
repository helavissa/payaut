package products;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bread implements ShoppingItem{

    private final String name = "Bread";
    private LocalDate dateProduced;

    public Bread(LocalDate dateProduced) {
        this.dateProduced = dateProduced;
    }

    public LocalDate getDateProduced() {
        return dateProduced;
    }

    public void setDateProduced(LocalDate dateProduced) {
        this.dateProduced = dateProduced;
    }

    public String getProductName(){
        return name;
    }

    public boolean isTwoDaysOldOrLess(){
        if(DAYS.between(getDateProduced(), LocalDate.now()) <= 2){
            return true;
        }
        return false;
    }

    public boolean isBetweenThreeAndFiveDaysOld(){
        if(DAYS.between(getDateProduced(), LocalDate.now()) >= 3 &&
                DAYS.between(getDateProduced(), LocalDate.now()) <= 5){
            return true;
        }
        return false;
    }

    public boolean isSixDaysOld(){
        if(DAYS.between(getDateProduced(), LocalDate.now()) == 6){
            return true;
        }
        return false;
    }

    public boolean isTooOld(){
        if(DAYS.between(getDateProduced(), LocalDate.now()) > 6){
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("2.00");
    }
}

