package Domain.Models.DepositChanges;

import Domain.Services.Calculators.PercentForPeriodCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class PercentageDepositChange extends DepositChange {
    protected BigDecimal percent;

    public PercentageDepositChange(LocalDate periodStartDate, LocalDate periodEndDate,  BigDecimal annualPercent) {
        this.percent = PercentForPeriodCalculator.CalculatePercentForPeriod(periodStartDate,periodEndDate,annualPercent);
        this.date = periodEndDate;
    }
}
