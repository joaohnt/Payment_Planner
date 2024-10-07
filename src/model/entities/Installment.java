package model.entities;

public class Installment {
    private String dueDate;
    private Double amount;

    public Installment(String dueDate, Double amount) {
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
