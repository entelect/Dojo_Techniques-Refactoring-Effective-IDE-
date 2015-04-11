package za.co.entelect.refactoring1.domain;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate) {
        super(balanceInCents, creditInterestsRate, debitInterestRate);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
}
