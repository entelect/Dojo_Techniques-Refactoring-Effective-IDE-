package za.co.entelect.refactoring1.domain;

public class MoneyMarketAccount extends BankAccount {

    public MoneyMarketAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate) {
        super(balanceInCents, creditInterestsRate, debitInterestRate);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.MONEY_MARKET;
    }
}
