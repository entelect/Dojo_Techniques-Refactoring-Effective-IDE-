package za.co.entelect.refactoring_final.domain;

public class SavingsAccount extends BankAccount {

    public static final double SAVINGS_CREDIT_INTEREST_RATE = 0.05D;
    public static final double SAVINGS_DEBIT_INTEREST_RATE = Double.NaN;
    public static final long SAVINGS_ACCOUNT_FEE = 1000;

    public SavingsAccount(Long balanceInCents) {
        super(balanceInCents, SAVINGS_CREDIT_INTEREST_RATE, SAVINGS_DEBIT_INTEREST_RATE, SAVINGS_ACCOUNT_FEE);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
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
