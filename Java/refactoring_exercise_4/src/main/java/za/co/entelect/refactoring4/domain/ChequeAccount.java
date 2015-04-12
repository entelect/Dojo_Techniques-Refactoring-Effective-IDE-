package za.co.entelect.refactoring4.domain;

public class ChequeAccount extends BankAccount {

    public static final double CHEQUE_CREDIT_INTEREST_RATE = 0.04D;
    public static final double CHEQUE_DEBIT_INTEREST_RATE = 0.12D;
    public static final long CHEQUE_ACCOUNT_FEE = 1400;

    public ChequeAccount(Long balanceInCents) {
        super(balanceInCents, CHEQUE_CREDIT_INTEREST_RATE, CHEQUE_DEBIT_INTEREST_RATE, CHEQUE_ACCOUNT_FEE);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHEQUE;
    }

    @Override
    public void calculateInterest() {
        if(getBalanceInCents() < 0){
            updateBalance((long) (getBalanceInCents() * getDebitInterestRate()));
        }else{
            updateBalance((long) (getBalanceInCents() * getCreditInterestsRate()));
        }
    }

    @Override
    public void calculateBalance(long amountInCents) {
        updateBalance(amountInCents);
    }
}
