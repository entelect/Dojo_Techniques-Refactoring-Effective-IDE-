package za.co.entelect.refactoring1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        if(bankAccount.getAccountType() == AccountType.SAVINGS){
            //negative balances are not supported
            if(bankAccount.getBalanceInCents() < 0){
                throw new RuntimeException("Negative balance not allowed");
            }
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }

        //calculate the balance for a cheque account
        if(bankAccount.getAccountType() == AccountType.CHEQUE){
            if(bankAccount.getBalanceInCents() < 0){
                bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getDebitInterestRate()));
            }else{
                bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
            }
        }

        //calculate the balance for a cheque money market account
        if(bankAccount.getAccountType() == AccountType.MONEY_MARKET){
            //negative balances are not allowed
            if(bankAccount.getBalanceInCents() < 0){
                throw new RuntimeException("Negative balance not allowed");
            }
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }
}