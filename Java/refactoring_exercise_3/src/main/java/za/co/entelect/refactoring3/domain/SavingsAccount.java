package za.co.entelect.refactoring3.domain;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate, long accountFee) {
        super(balanceInCents, creditInterestsRate, debitInterestRate, accountFee);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
}
