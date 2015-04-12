package za.co.entelect.refactoring_final.domain;

public class MoneyMarketAccount extends BankAccount {

    public static final double MONEY_MARKET_CREDIT_INTEREST_RATE = 0.1D;
    public static final double MONEY_MARKET_DEBIT_INTEREST_RATE = Double.NaN;
    public static final long MONEY_MARKET_ACCOUNT_FEE = 1200;

    public MoneyMarketAccount(Long balanceInCents) {
        super(balanceInCents, MONEY_MARKET_CREDIT_INTEREST_RATE, MONEY_MARKET_DEBIT_INTEREST_RATE, MONEY_MARKET_ACCOUNT_FEE);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.MONEY_MARKET;
    }

    @Override
    public void calculateInterest() {
        hasNegativeBalance();
        updateBalance((long) (getBalanceInCents() * getCreditInterestsRate()));
    }

    @Override
    public void calculateBalance(long amountInCents) {
        hasSufficientFunds(amountInCents);
        updateBalance(amountInCents);
    }
}
