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

// this class calculates the updated balance for bank accounts
public class AccountService {

    public void calculateInterest(BankAccount bankAccount){
        //calculate the balance for a savings account
        if(AccountType.SAVINGS == bankAccount.getAccountType()){
            //check has negative balance
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
        AccountType accountType = bankAccount.getAccountType();
        if(AccountType.MONEY_MARKET == accountType){
            //check has negative balance
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
        AccountType accountType = bankAccount.getAccountType();
        if(AccountType.CHEQUE == accountType){
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