package Presentation.ViewModels;

import Domain.Models.DepositChanges.DepositChange;
import Domain.Models.DepositChanges.UserDepositChange;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * ВМ для формы ввода данных.
 */
public class MainViewModel {

    /**
     * Изначальная сумма вклада.
     */
    private DoubleProperty initialAmount = new SimpleDoubleProperty(0);

    /**
     * Годовая процентная ставка.
     */
    private DoubleProperty annualPercent = new SimpleDoubleProperty(10);

    /**
     * Дата открытия вклада.
     */
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(LocalDate.now());

    /**
     * Срок вклада в днях.
     */
    private IntegerProperty periodInDays = new SimpleIntegerProperty(1);

    /**
     * Наличие капитализации.
     */
    private BooleanProperty hasCapitalisation = new SimpleBooleanProperty(false);

    /**
    * Внесения.
     */
    private ObservableList<DepositChangeVm> additions  = FXCollections.observableArrayList();

    /**
     * Снятия.
     */
    private ObservableList<DepositChangeVm> withdrawals  = FXCollections.observableArrayList();

    public BigDecimal getInitialAmount() {
        return BigDecimal.valueOf(initialAmount.get());
    }

    public DoubleProperty initialAmountProperty() {
        return initialAmount;
    }

    public BigDecimal getAnnualPercent() {
        return BigDecimal.valueOf(annualPercent.get()).divide(BigDecimal.valueOf(100),RoundingMode.HALF_UP);
    }

    public DoubleProperty annualPercentProperty() {
        return annualPercent;
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public int getPeriodInDays() {
        return periodInDays.get();
    }

    public IntegerProperty periodInDaysProperty() {
        return periodInDays;
    }

    public boolean isHasCapitalisation() {
        return hasCapitalisation.get();
    }

    public BooleanProperty hasCapitalisationProperty() {
        return hasCapitalisation;
    }

    public ObservableList<DepositChangeVm> getAdditions() {
        return additions;
    }

    public ObservableList<DepositChangeVm> getWithdrawals() {
        return withdrawals;
    }

    public List<UserDepositChange> getUserDepositChanges() {
        var changes = Stream.concat(withdrawals.stream(),additions.stream()).map(DepositChangeVm::getDomainModel);
        return changes.collect(Collectors.toList());
    }
}
