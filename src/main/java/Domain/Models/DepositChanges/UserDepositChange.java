package Domain.Models.DepositChanges;
import Domain.Models.DepositStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserDepositChange extends DepositChange {
    private Double amount;

    public UserDepositChange(LocalDate date, Double amount) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public void ApplyDepositChange(DepositStatus depositStatus) {
        depositStatus.addToTotalAmount(BigDecimal.valueOf(amount));
    }
}
