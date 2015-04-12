package za.co.entelect.refactoring4.domain;

public class ChequeAccount extends BankAccount {

    public ChequeAccount(Long balanceInCents, double creditInterestsRate, double debitInterestRate, long accountFee) {
        super(balanceInCents, creditInterestsRate, debitInterestRate, accountFee);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHEQUE;
    }
}
