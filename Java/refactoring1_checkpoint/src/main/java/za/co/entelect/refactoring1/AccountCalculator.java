package za.co.entelect.refactoring1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This exercise demonstrates the following code smells
 * 1. Comments
 * 2. Long Method
 * 3. Duplication
 * 4. Bad Naming
 */

// this class calculates the updated balance for bank accounts
public class AccountCalculator {

    public void calculateNewBalance(BankAccount bankAccount){
        //calculate the balance for a savings account
        if(bankAccount.getAccountType() == AccountType.SAVINGS){
            //negative balances are not supported
            checkNegativeBalance(bankAccount);
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
            checkNegativeBalance(bankAccount);
            bankAccount.updateBalance((long) (bankAccount.getBalanceInCents() * bankAccount.getCreditInterestsRate()));
        }
    }

    private void checkNegativeBalance(BankAccount bankAccount) {
        if(bankAccount.getBalanceInCents() < 0){
            throw new RuntimeException("Negative balance not allowed");
        }
    }
}