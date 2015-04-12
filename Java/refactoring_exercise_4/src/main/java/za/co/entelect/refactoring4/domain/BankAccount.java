package za.co.entelect.refactoring4.domain;

import za.co.entelect.refactoring4.exception.BankAccountException;

/* Exercise 3:
 *
 * This exercise demonstrates the following code smells
 *
 * 1. Shotgun surgery/Inappropriate Intimacy : The constants should exist in the appropriate classes as well as the methods to debit the account and calculate interest
 *
 */
public abstract class BankAccount {

    public static final double SAVINGS_CREDIT_INTEREST_RATE = 0.05D;
    public static final double SAVINGS_DEBIT_INTEREST_RATE = Double.NaN;
    public static final long SAVINGS_ACCOUNT_FEE = 1000;

    public static final double MONEY_MARKET_CREDIT_INTEREST_RATE = 0.1D;
    public static final double MONEY_MARKET_DEBIT_INTEREST_RATE = Double.NaN;
    public static final long MONEY_MARKET_ACCOUNT_FEE = 1200;

    public static final double CHEQUE_CREDIT_INTEREST_RATE = 0.04D;
    public static final double CHEQUE_DEBIT_INTEREST_RATE = 0.12D;
    public static final long CHEQUE_ACCOUNT_FEE = 1400;

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

    public boolean isMoneyMarketAccount() {
        return AccountType.MONEY_MARKET == getAccountType();
    }

    public boolean isSavingAccount() {
        return AccountType.SAVINGS == getAccountType();
    }

    public boolean isChequeAccount() {
        return AccountType.CHEQUE == getAccountType();
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

    public void calculateNonCreditAccountInterest() {
        hasNegativeBalance();
        updateBalance((long) (getBalanceInCents() * getCreditInterestsRate()));
    }

    public void calculateCreditAccountInterest() {
        if(getBalanceInCents() < 0){
            updateBalance((long) (getBalanceInCents() * getDebitInterestRate()));
        }else{
            updateBalance((long) (getBalanceInCents() * getCreditInterestsRate()));
        }
    }

    public void calculateInterest(){
        if(isSavingAccount() || isMoneyMarketAccount()){
            calculateNonCreditAccountInterest();
        }

        if(isChequeAccount()){
            calculateCreditAccountInterest();
        }
    }

    public void calculateBalance(long amountInCents){
        if(isSavingAccount() || isMoneyMarketAccount()){
            hasSufficientFunds(amountInCents);
            updateBalance(amountInCents);
        }

        if(isChequeAccount()){
            updateBalance(amountInCents);
        }
    }
}
