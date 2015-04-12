package za.co.entelect.refactoring_final.domain;

import za.co.entelect.refactoring_final.exception.BankAccountException;

public abstract class BankAccount {

    private final double creditInterestsRate;
    private final double debitInterestRate;
    private final long feeInCents;

    private Long balanceInCents;
    private boolean accountActive = true;

    public BankAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate, long fee) {
        this.balanceInCents = balanceInCents;
        this.creditInterestsRate = creditInterestsRate;
        this.debitInterestRate = debitInterestRate;
        this.feeInCents = fee;
    }

    public abstract AccountType getAccountType();

    public long getBalanceInCents() {
        return balanceInCents;
    }

    public void updateBalance(long balanceInCents) {
        this.balanceInCents += balanceInCents;
    }

    public double getCreditInterestsRate() {
        return creditInterestsRate;
    }

    public double getDebitInterestRate() {
        return debitInterestRate;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public void closeAccount() {
        this.accountActive = false;
    }

    public void reopenAccount() {
        this.accountActive = true;
    }

    public long getFeeInCents() {
        return feeInCents;
    }

    public void hasSufficientFunds(long amountInCents) {
        if(getBalanceInCents() + amountInCents < 0){
            throw new BankAccountException("Insufficient funds");
        }
    }

    public void hasNegativeBalance() {
        if(getBalanceInCents() < 0){
            throw new BankAccountException("Negative balance not allowed");
        }
    }

    public abstract void calculateInterest();
    public abstract void calculateBalance(long amountInCents);
}
