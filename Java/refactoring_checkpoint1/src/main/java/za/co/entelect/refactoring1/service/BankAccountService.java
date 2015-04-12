package za.co.entelect.refactoring1.service;

import za.co.entelect.refactoring1.domain.AccountType;
import za.co.entelect.refactoring1.domain.BankAccount;
import za.co.entelect.refactoring1.exception.BankAccountException;

import java.util.ArrayList;
import java.util.List;

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

// this class calculates the updated balance for bank accounts
public class BankAccountService {

    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public int countBanksAccounts() {
        return bankAccounts.size();
    }

    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public void calculateInterest(BankAccount bankAccount){
        if(isSavingAccount(bankAccount) || isMoneyMarketAccount(bankAccount)){
            updateInterestNonCreditAccount(bankAccount);
        }

        if(isChequeAccount(bankAccount)){
            updateInterestCreditAccount(bankAccount);
        }
    }

    public void updateBalance(BankAccount bankAccount, long amountInCents){

        if(isSavingAccount(bankAccount) || isMoneyMarketAccount(bankAccount)){
            debitNonCreditAccount(bankAccount, amountInCents);
        }

        if(isChequeAccount(bankAccount)){
            bankAccount.updateBalance(amountInCents);
        }
    }

    private void updateInterestCreditAccount(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getDebitInterestRate()));
        }else{
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }

    private void updateInterestNonCreditAccount(BankAccount bankAccount) {
        hasNegativeBalance(bankAccount);
        bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
    }

    private void debitNonCreditAccount(BankAccount bankAccount, long amountInCents) {
        hasSufficientFunds(bankAccount, amountInCents);
        bankAccount.updateBalance(amountInCents);
    }

    private void hasNegativeBalance(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            throw new BankAccountException("Negative balance not allowed");
        }
    }

    private void hasSufficientFunds(BankAccount bankAccount, long amountInCents) {
        if(bankAccount.getBalanceInCents() + amountInCents < 0){
            throw new BankAccountException("Insufficient funds");
        }
    }

    private boolean isChequeAccount(BankAccount bankAccount) {
        return AccountType.CHEQUE == bankAccount.getAccountType();
    }

    private boolean isMoneyMarketAccount(BankAccount bankAccount) {
        return AccountType.MONEY_MARKET == bankAccount.getAccountType();
    }

    private boolean isSavingAccount(BankAccount bankAccount) {
        return AccountType.SAVINGS == bankAccount.getAccountType();
    }
}