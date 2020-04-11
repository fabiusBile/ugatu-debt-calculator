package Domain.Services.Calculators;

import Domain.Models.DepositChanges.DepositChange;
import Domain.Models.DepositChanges.PercentageDepositChange;
import Domain.Models.DepositChanges.UserDepositChange;
import Domain.Models.DepositStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Базовый класс для калькуляторов депозита.
 */
public abstract class BaseDebtCalculator {

    protected abstract List<LocalDate> GetPeriods(LocalDate startDate, int days);

    protected abstract PercentageDepositChange GetDepositChange(LocalDate periodStartDate, LocalDate periodEndDate, BigDecimal annualPercent);

    public DepositStatus CalculateDebt(BigDecimal initialAmount, BigDecimal annualPercent, LocalDate startDate, int days, List<UserDepositChange> userDepositChanges){
        var endDate = startDate.plusDays(days);
        var periods = GetPeriods(startDate,days);

        if (periods.get(periods.size() -1 ).isBefore(endDate)){
            periods.add(endDate);
        }

        List<DepositChange> depositChanges = new ArrayList<>();

        for (var i = 1; i!= periods.size();i++){
            depositChanges.add(GetDepositChange(periods.get(i-1),periods.get(i),annualPercent));
        }

        if (userDepositChanges != null){
            depositChanges.addAll(userDepositChanges);
        }
        depositChanges.sort(Comparator.comparing(DepositChange::getDate));

        var depositStatus = new DepositStatus(initialAmount);

        for (var depositChange : depositChanges) {
            depositChange.ApplyDepositChange(depositStatus);
        }

        return depositStatus;
    }
}
