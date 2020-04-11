package Domain.Services.Factories;

import Domain.Models.PercentPaymentPeriodType;

import java.time.Period;

public class PercentPaymentPeriodFactory {
    public static Period getPeriodForType(PercentPaymentPeriodType periodType){
        switch (periodType) {
            case Monthly:
                return Period.ofMonths(1);
            case Quarterly:
                return Period.ofMonths(3);
            case Annually:
                return Period.ofYears(1);
        }

        throw new IllegalArgumentException(periodType.name());
    }
}
