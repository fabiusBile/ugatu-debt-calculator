package Domain.Services.Calculators;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PercentForPeriodCalculator {
    public static BigDecimal CalculatePercentForPeriod(LocalDate startDate, LocalDate endDate, BigDecimal yearPercent){
        var length = BigDecimal.valueOf(startDate.until(endDate,ChronoUnit.DAYS));
        var daysOfYear = BigDecimal.valueOf(startDate.lengthOfYear());

        return   (yearPercent.divide(daysOfYear, MathContext.DECIMAL32)).multiply(length,MathContext.DECIMAL32);
    }
}
