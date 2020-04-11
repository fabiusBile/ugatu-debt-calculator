package Domain.Services;

import Domain.Models.DepositChanges.UserDepositChange;
import Domain.Services.Calculators.SimpleDebtCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class SimpleDebtCalculatorTest {

    @Test
    void calculateDebt() {
        var calculator = new SimpleDebtCalculator();
        var result =  calculator.CalculateDebt(BigDecimal.valueOf(1000),BigDecimal.valueOf(0.1d), LocalDate.of(2020,1,31),365,null );
        Assertions.assertEquals(BigDecimal.valueOf(99.74),result.getPercentsPaid());
        Assertions.assertEquals(BigDecimal.valueOf(1099.74),result.getTotalAmount());
    }

    @Test
    void calculateDebtWithUserChanges(){
        var calculator = new SimpleDebtCalculator();
        var userChanges = List.of(
                new UserDepositChange(LocalDate.of(2020,3,31),1000d),
                new UserDepositChange(LocalDate.of(2020,5,31),1000d));

        var result =  calculator.CalculateDebt(BigDecimal.valueOf(1000),BigDecimal.valueOf(0.1d), LocalDate.of(2020,1,31),365,userChanges);

        Assertions.assertEquals(BigDecimal.valueOf(249.72),result.getPercentsPaid());
        Assertions.assertEquals(BigDecimal.valueOf(3249.72),result.getTotalAmount());
    }
}