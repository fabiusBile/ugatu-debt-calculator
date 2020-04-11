package Domain.Services.Calculators;

import Domain.Models.DepositChanges.CapitalisationPercentDepositChange;
import Domain.Models.DepositChanges.PercentageDepositChange;
import Domain.Models.PercentPaymentPeriodType;
import Domain.Services.Factories.PercentPaymentPeriodFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Депозитный калькулятор с учетом капитализации.
 */
public class CapitalisationDebtCalculator extends BaseDebtCalculator {

    private Period paymentPeriod;

    public CapitalisationDebtCalculator(PercentPaymentPeriodType periodType) {
        paymentPeriod = PercentPaymentPeriodFactory.getPeriodForType(periodType);
    }

    @Override
    protected List<LocalDate> GetPeriods(LocalDate startDate, int days) {
        var endDate = startDate.plusDays(days);
        var periods = startDate.datesUntil(endDate, paymentPeriod).collect(Collectors.toList());
        if (periods.get(periods.size() -1 ).isBefore(endDate)){
            periods.add(endDate);
        }

        return periods;
    }

    @Override
    protected PercentageDepositChange GetDepositChange(LocalDate periodStartDate,
                                                       LocalDate periodEndDate,
                                                       BigDecimal annualPercent) {
        return new CapitalisationPercentDepositChange(periodStartDate,periodEndDate,annualPercent);
    }
}
