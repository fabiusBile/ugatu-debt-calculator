package Domain.Models.DepositChanges;

import Domain.Models.DepositStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class SimplePercentDepositChange extends PercentageDepositChange {

    public SimplePercentDepositChange(LocalDate periodStartDate, LocalDate periodEndDate, BigDecimal annualPercent) {
        super(periodStartDate, periodEndDate, annualPercent);
    }

    @Override
    public void ApplyDepositChange(DepositStatus depositStatus) {
        if (depositStatus.getTotalAmount().compareTo(BigDecimal.ZERO) > 0){
            depositStatus.addPercents(depositStatus.getTotalAmount().multiply(percent).setScale(2, RoundingMode.HALF_UP));
        }
    }
}
