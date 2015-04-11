package za.co.entelect.refactoring1;

public class AccountBalanceCalculator {

    public void calculateNewBalance(BankAccount bankAccount){

        if(isSavingAccount(bankAccount)){
            updateNonCreditAccount(bankAccount);
        }

        if(isChequeAccount(bankAccount)){
            updateCreditAccount(bankAccount);
        }

        if(isMoneyMarketAccount(bankAccount)){
            updateNonCreditAccount(bankAccount);
        }
    }

    private void updateCreditAccount(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getDebitInterestRate()));
        }else{
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }

    private void updateNonCreditAccount(BankAccount bankAccount) {
        checkNegativeBalance(bankAccount);
        bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
    }

    private void checkNegativeBalance(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            throw new RuntimeException("Negative balance not allowed");
        }
    }

    private boolean isMoneyMarketAccount(BankAccount bankAccount) {
        return bankAccount.getAccountType() == AccountType.MONEY_MARKET;
    }

    private boolean isChequeAccount(BankAccount bankAccount) {
        return bankAccount.getAccountType() == AccountType.CHEQUE;
    }

    private boolean isSavingAccount(BankAccount bankAccount) {
        return bankAccount.getAccountType() == AccountType.SAVINGS;
    }
}