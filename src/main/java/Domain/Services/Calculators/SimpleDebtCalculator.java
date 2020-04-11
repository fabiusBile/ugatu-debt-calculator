package Domain.Services.Calculators;

import Domain.Models.DepositChanges.PercentageDepositChange;
import Domain.Models.DepositChanges.SimplePercentDepositChange;
import Domain.Models.DepositChanges.UserDepositChange;
import Domain.Models.DepositStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Депозитный калькулятор без капитализации
 */
public class SimpleDebtCalculator extends BaseDebtCalculator {
    @Override
    protected List<LocalDate> GetPeriods(LocalDate startDate, int days) {
        var endDate = startDate.plusDays(days);
        var months = startDate.datesUntil(endDate, Period.ofMonths(1)).collect(Collectors.toList());
        if (months.get(months.size() -1 ).isBefore(endDate)){
            months.add(endDate);
        }

        return months;
    }

    @Override
    protected PercentageDepositChange GetDepositChange(LocalDate periodStartDate,
                                                       LocalDate periodEndDate,
                                                       BigDecimal annualPercent) {
        return new SimplePercentDepositChange(periodStartDate,periodEndDate,annualPercent);
    }

    @Override
    public DepositStatus CalculateDebt(BigDecimal initialAmount, BigDecimal annualPercent, LocalDate startDate, int days, List<UserDepositChange> userDepositChanges){
       var depositStatus = super.CalculateDebt(initialAmount,annualPercent,startDate,days, userDepositChanges);
       depositStatus.addToTotalAmount(depositStatus.getPercentsPaid());
       return depositStatus;
    }
}
