package Domain.Models.DepositChanges;

import Domain.Models.DepositStatus;

import java.time.LocalDate;

/**
 * Модель информации о изменении баланса.
 */
public abstract class DepositChange {
    protected LocalDate date;

    public abstract void ApplyDepositChange(DepositStatus depositStatus);

    public LocalDate getDate() {
        return date;
    }
}
