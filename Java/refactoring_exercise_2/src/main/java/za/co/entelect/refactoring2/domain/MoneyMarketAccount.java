package za.co.entelect.refactoring2.domain;

public class MoneyMarketAccount extends BankAccount {

    public MoneyMarketAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate, long accountFee) {
        super(balanceInCents, creditInterestsRate, debitInterestRate, accountFee);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.MONEY_MARKET;
    }
}
