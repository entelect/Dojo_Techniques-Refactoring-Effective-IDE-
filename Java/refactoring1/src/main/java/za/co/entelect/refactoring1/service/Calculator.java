package za.co.entelect.refactoring1.service;

import za.co.entelect.refactoring1.domain.AccountType;
import za.co.entelect.refactoring1.domain.BankAccount;
import za.co.entelect.refactoring1.exception.BankAccountException;

/**
 * This exercise demonstrates the following code smells
 * 1. Bad Naming
 * 2. Comments
 * 3. Long Method
 * 4. Duplication
 */

// this class calculates the updated balance for bank accounts
public class Calculator {

    public void calculate(BankAccount bankAccount){
        //calculate the balance for a savings account
        if(AccountType.SAVINGS == bankAccount.getAccountType()){
            //negative balances are not supported
            if(bankAccount.getBalanceInCents() < 0){
                throw new BankAccountException("Negative balance not allowed");
            }
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }

        //calculate the balance for a cheque account
        if(AccountType.CHEQUE == bankAccount.getAccountType()){
            if(bankAccount.getBalanceInCents() < 0){
                bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getDebitInterestRate()));
            }else{
                bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
            }
        }

        //calculate the balance for a cheque money market account
        if(AccountType.MONEY_MARKET == bankAccount.getAccountType()){
            //negative balances are not allowed
            if(bankAccount.getBalanceInCents() < 0){
                throw new BankAccountException("Negative balance not allowed");
            }
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }

    public void updateBalance(BankAccount bankAccount, long amountInCents){
        //update the balance for a savings account
        if(AccountType.SAVINGS == bankAccount.getAccountType()){
            //cannot go into negative balance
            if(bankAccount.getBalanceInCents() + amountInCents < 0){
                throw new BankAccountException("Insufficient funds");
            }
            bankAccount.updateBalance(amountInCents);
        }

        //update the balance for a cheque account
        if(AccountType.CHEQUE == bankAccount.getAccountType()){
            bankAccount.updateBalance(amountInCents);
        }

        //calculate the balance for a cheque money market account
        if( AccountType.MONEY_MARKET == bankAccount.getAccountType()){
            //cannot go into negative balance
            if(bankAccount.getBalanceInCents() + amountInCents < 0){
                throw new BankAccountException("Insufficient funds");
            }
            bankAccount.updateBalance(amountInCents);
        }
    }
}