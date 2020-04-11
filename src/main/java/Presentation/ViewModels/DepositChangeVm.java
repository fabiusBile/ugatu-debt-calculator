package Presentation.ViewModels;

import Domain.Models.DepositChanges.UserDepositChange;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

/*
ВМ для внесения или снятия средств.
 */
public class DepositChangeVm {
    private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>(LocalDate.now());

    private boolean isAddition;

    private SimpleDoubleProperty amount = new SimpleDoubleProperty(0);

    public DepositChangeVm(boolean isAddition) {
        this.isAddition = isAddition;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public Property<LocalDate> dateProperty() {
        return date;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public UserDepositChange getDomainModel(){
        return new UserDepositChange(getDate(),getAmount() * (isAddition ? 1 : -1));
    }
}
