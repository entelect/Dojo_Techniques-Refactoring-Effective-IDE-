package za.co.entelect.refactoring1.controller;

import za.co.entelect.refactoring1.Calculator;
import za.co.entelect.refactoring1.domain.BankAccount;
import za.co.entelect.refactoring1.exception.BankAccountException;

public class BankingController {

    private static final long ACCOUNT_REOPEN_FEE_CENT = 2000;

    private Calculator calculator = new Calculator();

    public void updateAccount(BankAccount bankAccount, BankingAction bankingAction){
        switch (bankingAction){
            case RECALCULATE_BALANCE :
                calculator.calculate(bankAccount);
                break;
            case CLOSE_ACCOUNT :
                if(bankAccount.isAccountActive()){
                    bankAccount.closeAccount();
                }
                break;
            case REOPEN_ACCOUNT:
                if(!bankAccount.isAccountActive()){
                    bankAccount.getBalanceInCents();
                    if(bankAccount.getBalanceInCents() - ACCOUNT_REOPEN_FEE_CENT < 0){
                        throw new BankAccountException("Account does not contain a balance to debit the reopen fee");
                    }else{
                        bankAccount.updateBalance(-ACCOUNT_REOPEN_FEE_CENT);
                        bankAccount.reopenAccount();
                    }
                }
                break;

        }
    }
}