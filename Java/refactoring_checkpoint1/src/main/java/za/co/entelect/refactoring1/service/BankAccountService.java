package za.co.entelect.refactoring1.service;

import za.co.entelect.refactoring1.domain.AccountType;
import za.co.entelect.refactoring1.domain.BankAccount;
import za.co.entelect.refactoring1.exception.BankAccountException;

/**
 * Exercise 1:
 *
 * This exercise demonstrates the following code smells
 * 1. Bad Naming
 * 2. Comments
 * 3. Long Method
 * 4. Duplication
 *
 *
 * Exercise 2:
 *
 * After the refactoring of exercise 1 we are still left with the following smells
 *
 * 1. Feature envy
 *
 */

public class BankAccountService {

    public void calculateInterest(BankAccount bankAccount){
        if(isSavingAccount(bankAccount) || isMoneyMarketAccount(bankAccount)){
            updateNonCreditAccountInterest(bankAccount);
        }
        if(isChequeAccount(bankAccount)){
            updateCreditAccountInterest(bankAccount);
        }
    }

    public void updateBalance(BankAccount bankAccount, long amountInCents){
        if(isSavingAccount(bankAccount) || isMoneyMarketAccount(bankAccount)){
            hasSufficientFunds(bankAccount, amountInCents);
            bankAccount.updateBalance(amountInCents);
        }

        if(isChequeAccount(bankAccount)){
            bankAccount.updateBalance(amountInCents);
        }
    }

    private void updateCreditAccountInterest(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getDebitInterestRate()));
        }else{
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }

    private void updateNonCreditAccountInterest(BankAccount bankAccount) {
        checkNegativeBalance(bankAccount);
        bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
    }

    private void hasSufficientFunds(BankAccount bankAccount, long amountInCents) {
        if(bankAccount.getBalanceInCents() + amountInCents < 0){
            throw new BankAccountException("Insufficient funds");
        }
    }

    private void checkNegativeBalance(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            throw new BankAccountException("Negative balance not allowed");
        }
    }

    private boolean isMoneyMarketAccount(BankAccount bankAccount) {
        return AccountType.MONEY_MARKET == bankAccount.getAccountType();
    }

    private boolean isSavingAccount(BankAccount bankAccount) {
        return AccountType.SAVINGS == bankAccount.getAccountType();
    }

    private boolean isChequeAccount(BankAccount bankAccount) {
        return AccountType.CHEQUE == bankAccount.getAccountType();
    }
}