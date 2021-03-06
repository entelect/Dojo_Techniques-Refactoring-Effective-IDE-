package za.co.entelect.refactoring_final.controller;

import org.junit.Test;
import za.co.entelect.refactoring_final.domain.BankingAction;
import za.co.entelect.refactoring_final.domain.ChequeAccount;
import za.co.entelect.refactoring_final.domain.SavingsAccount;
import za.co.entelect.refactoring_final.exception.BankAccountException;

import static junit.framework.Assert.*;

public class BankingControllerTest {

    public static final long INITIAL_BALANCE = 3000L;

    private BankingController bankingController = new BankingController();

    @Test
    public void testRecalculateBalance(){
        SavingsAccount savingsAccount = createSavingsAccount(INITIAL_BALANCE);
        bankingController.updateAccount(savingsAccount, BankingAction.RECALCULATE_BALANCE);
        assertEquals(3150L, savingsAccount.getBalanceInCents());
    }

    @Test
    public void testCloseAccount(){
        SavingsAccount savingsAccount = createSavingsAccount(INITIAL_BALANCE);
        assertTrue(savingsAccount.isAccountActive());

        bankingController.updateAccount(savingsAccount, BankingAction.CLOSE_ACCOUNT);
        assertFalse(savingsAccount.isAccountActive());

        bankingController.updateAccount(savingsAccount, BankingAction.CLOSE_ACCOUNT);
        assertFalse(savingsAccount.isAccountActive());
    }

    @Test
    public void testReopenAccount(){
        SavingsAccount savingsAccount = createSavingsAccount(INITIAL_BALANCE);
        savingsAccount.closeAccount();

        assertFalse(savingsAccount.isAccountActive());
        bankingController.updateAccount(savingsAccount, BankingAction.REOPEN_ACCOUNT);

        assertTrue(savingsAccount.isAccountActive());
    }

    @Test
    public void testReopenOpenSavingsAccount(){
        SavingsAccount savingsAccount = createSavingsAccount(INITIAL_BALANCE);
        assertTrue(savingsAccount.isAccountActive());
        bankingController.updateAccount(savingsAccount, BankingAction.REOPEN_ACCOUNT);
        assertTrue(savingsAccount.isAccountActive());
    }

    @Test
    public void testReopenChequeAccount(){
        ChequeAccount chequeAccount = createChequeAccount(0);
        assertTrue(chequeAccount.isAccountActive());
        bankingController.updateAccount(chequeAccount, BankingAction.REOPEN_ACCOUNT);
        assertTrue(chequeAccount.isAccountActive());
    }

    @Test(expected = BankAccountException.class)
    public void testReopenAccountFail(){
        SavingsAccount savingsAccount = createSavingsAccount(0L);
        savingsAccount.closeAccount();
        bankingController.updateAccount(savingsAccount, BankingAction.REOPEN_ACCOUNT);
    }

    @Test
    public void testChargeAccountFee(){
        SavingsAccount savingsAccount = createSavingsAccount(INITIAL_BALANCE);
        bankingController.updateAccount(savingsAccount, BankingAction.CHARGE_ACCOUNT_FEE);
        assertEquals(savingsAccount.getBalanceInCents(), INITIAL_BALANCE - SavingsAccount.SAVINGS_ACCOUNT_FEE);
    }

    private SavingsAccount createSavingsAccount(long balance) {
        return new SavingsAccount(balance);
    }

    private ChequeAccount createChequeAccount(long balance) {
        return new ChequeAccount(balance);
    }
}