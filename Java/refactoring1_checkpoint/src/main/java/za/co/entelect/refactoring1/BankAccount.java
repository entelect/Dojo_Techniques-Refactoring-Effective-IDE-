package za.co.entelect.refactoring1;

public class BankAccount {

    public static final double SAVINGS_CREDIT_INTEREST_RATE = 0.05D;
    public static final double SAVINGS_DEBIT_INTEREST_RATE = Double.NaN;

    public static final double MONEY_MARKET_CREDIT_INTEREST_RATE = 0.1D;
    public static final double MONEY_MARKET_DEBIT_INTEREST_RATE = Double.NaN;

    public static final double CHEQUE_CREDIT_INTEREST_RATE = 0.04D;
    public static final double CHEQUE_MARKET_DEBIT_INTEREST_RATE = 0.12D;

    private final AccountType accountType;
    private final double creditInterestsRate;
    private final double debitInterestRate;
    private Long balanceInCents;

    public BankAccount(AccountType accountType, Long balanceInCents, double creditInterestsRate, double debitInterestRate) {
        this.accountType = accountType;
        this.balanceInCents = balanceInCents;
        this.creditInterestsRate = creditInterestsRate;
        this.debitInterestRate = debitInterestRate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

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
}
