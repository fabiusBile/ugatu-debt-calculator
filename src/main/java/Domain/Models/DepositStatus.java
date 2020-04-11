package Domain.Models;

import java.math.BigDecimal;

/**
 *  Состояние счета клиента после завершения периода вклада.
 */
public class DepositStatus {
    private BigDecimal totalAmount = BigDecimal.ZERO;

    private BigDecimal percentsPaid = BigDecimal.ZERO;

    public void addPercents(BigDecimal percentsAmount){
        percentsPaid = percentsPaid.add(percentsAmount);
    }

    public DepositStatus(BigDecimal initialAmount) {
        this.totalAmount = initialAmount;
    }

    public void addToTotalAmount(BigDecimal amount){
        totalAmount =  totalAmount.add(amount);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getPercentsPaid() {
        return percentsPaid;
    }

}
