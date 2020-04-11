package Domain.Models;

public enum PercentPaymentPeriodType {
    Monthly("Ежемесячно"),
    Quarterly("Ежеквартально"),
    Annually("Ежегодно");

    private String description;

    PercentPaymentPeriodType(String value) {
        this.description = value;
    }

    public String getValue() {
        return description;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
