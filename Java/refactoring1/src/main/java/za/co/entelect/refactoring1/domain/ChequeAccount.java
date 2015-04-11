package za.co.entelect.refactoring1.domain;

public class ChequeAccount extends BankAccount {

    public ChequeAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate) {
        super(balanceInCents, creditInterestsRate, debitInterestRate);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHEQUE;
    }
}
