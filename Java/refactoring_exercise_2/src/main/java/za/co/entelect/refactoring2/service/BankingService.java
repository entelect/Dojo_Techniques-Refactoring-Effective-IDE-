package za.co.entelect.refactoring2.service;

import za.co.entelect.refactoring2.domain.AccountType;
import za.co.entelect.refactoring2.domain.BankAccount;
import za.co.entelect.refactoring2.exception.BankAccountException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Exercise 2:
 *
 * After the refactoring of exercise 1 we are still left with the following smells
 *
 * 1. Feature envy : The class is too tightly coupled with the internals of the BankAccount
 *
 */
public class BankingService {

    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public int countBanksAccounts() {
        return bankAccounts.size();
    }

    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public void calculateInterest(BankAccount bankAccount){
        if(isSavingAccount(bankAccount) || isMoneyMarketAccount(bankAccount)){
            calculateNonCreditAccountInterest(bankAccount);
        }

        if(isChequeAccount(bankAccount)){
            calculateCreditAccountInterest(bankAccount);
        }
    }

    public void calculateBalance(BankAccount bankAccount, long amountInCents){
        if(isSavingAccount(bankAccount) || isMoneyMarketAccount(bankAccount)){
            hasSufficientFunds(bankAccount, amountInCents);
            bankAccount.updateBalance(amountInCents);
        }

        if(isChequeAccount(bankAccount)){
            bankAccount.updateBalance(amountInCents);
        }
    }

    private void calculateCreditAccountInterest(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getDebitInterestRate()));
        }else{
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }

    private void calculateNonCreditAccountInterest(BankAccount bankAccount) {
        hasNegativeBalance(bankAccount);
        bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
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

    private boolean isSavingAccount(BankAccount bankAccount) {
        return AccountType.SAVINGS == bankAccount.getAccountType();
    }

    private boolean isMoneyMarketAccount(BankAccount bankAccount) {
        return AccountType.MONEY_MARKET == bankAccount.getAccountType();
    }
}