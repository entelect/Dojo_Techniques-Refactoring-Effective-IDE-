package za.co.entelect.refactoring_final.controller;

import za.co.entelect.refactoring_final.domain.AccountType;
import za.co.entelect.refactoring_final.domain.BankAccount;
import za.co.entelect.refactoring_final.domain.BankingAction;
import za.co.entelect.refactoring_final.exception.BankAccountException;

import java.util.HashMap;


public class BankingController {

    private static final long ACCOUNT_REOPEN_FEE_CENTS = 2000;

    private HashMap<BankingAction, AccountStrategy> bankingActionAccountStrategyHashMap = new HashMap<BankingAction, AccountStrategy>();

    public BankingController() {
        bankingActionAccountStrategyHashMap.put(BankingAction.RECALCULATE_BALANCE, new RecalculateBalanceStrategy());
        bankingActionAccountStrategyHashMap.put(BankingAction.CLOSE_ACCOUNT, new CloseAccountStrategy());
        bankingActionAccountStrategyHashMap.put(BankingAction.REOPEN_ACCOUNT, new ReopenAccountStrategy());
        bankingActionAccountStrategyHashMap.put(BankingAction.CHARGE_ACCOUNT_FEE, new ChargeAccountFeeStrategy());

    }

    public void updateAccount(BankAccount bankAccount, BankingAction bankingAction){
        bankingActionAccountStrategyHashMap.get(bankingAction).updateAccount(bankAccount);
    }

    interface AccountStrategy {
        void updateAccount(BankAccount bankAccount);
    }

    private class RecalculateBalanceStrategy implements AccountStrategy{
        @Override
        public void updateAccount(BankAccount bankAccount) {
            bankAccount.calculateInterest();
        }
    }

    private class CloseAccountStrategy implements AccountStrategy{
        @Override
        public void updateAccount(BankAccount bankAccount) {
            if(bankAccount.isAccountActive()){
                bankAccount.closeAccount();
            }
        }
    }

    private class ReopenAccountStrategy implements AccountStrategy{
        @Override
        public void updateAccount(BankAccount bankAccount) {
            if(!bankAccount.isAccountActive()){
                if(AccountType.CHEQUE != bankAccount.getAccountType() && bankAccount.getBalanceInCents() - ACCOUNT_REOPEN_FEE_CENTS < 0){
                    throw new BankAccountException("Account does not contain a balance to debit the reopen fee");
                }else{
                    bankAccount.updateBalance(-ACCOUNT_REOPEN_FEE_CENTS);
                    bankAccount.reopenAccount();
                }
            }
        }
    }

    private class ChargeAccountFeeStrategy implements AccountStrategy{
        @Override
        public void updateAccount(BankAccount bankAccount) {
            bankAccount.calculateBalance(-bankAccount.getFeeInCents());
        }
    }
}