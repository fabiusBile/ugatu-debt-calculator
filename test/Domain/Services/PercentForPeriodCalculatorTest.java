package Domain.Services;

import Domain.Services.Calculators.PercentForPeriodCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

class PercentForPeriodCalculatorTest {

    @Test
    void calculatePercentForPeriod() {
        var result = PercentForPeriodCalculator.CalculatePercentForPeriod(LocalDate.of(2019,10,16),LocalDate.of(2019,11,16),BigDecimal.valueOf(0.1));
      Assertions.assertEquals(BigDecimal.valueOf(0.0085),result.setScale(4,RoundingMode.HALF_UP));
    }

    @Test
    void calculatePercentForZero() {
        var result = PercentForPeriodCalculator.CalculatePercentForPeriod(LocalDate.of(2019,10,16),LocalDate.of(2019,10,16), BigDecimal.valueOf(0.1));
        Assertions.assertEquals(BigDecimal.valueOf(0d),result);
    }
}