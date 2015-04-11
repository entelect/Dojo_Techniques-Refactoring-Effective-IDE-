package za.co.entelect.refactoring1.domain;

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
}
