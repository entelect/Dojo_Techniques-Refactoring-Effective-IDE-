package za.co.entlect.refactoring1.controller;

import org.junit.Test;
import za.co.entelect.refactoring1.controller.BankingAction;
import za.co.entelect.refactoring1.controller.BankingController;
import za.co.entelect.refactoring1.domain.BankAccount;
import za.co.entelect.refactoring1.domain.SavingsAccount;
import za.co.entelect.refactoring1.exception.BankAccountException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BankingControllerTest {

    private BankingController bankingController = new BankingController();

    @Test
    public void testRecalculateBalance(){
        SavingsAccount savingsAccount = createSavingsAccount(3000);

        bankingController.updateAccount(savingsAccount, BankingAction.RECALCULATE_BALANCE);

        assertEquals(3150L, savingsAccount.getBalanceInCents());
    }

    @Test
    public void testCloseAccount(){
        SavingsAccount savingsAccount = createSavingsAccount(3000L);
        assertTrue(savingsAccount.isAccountActive());

        bankingController.updateAccount(savingsAccount, BankingAction.CLOSE_ACCOUNT);
        assertFalse(savingsAccount.isAccountActive());

        bankingController.updateAccount(savingsAccount, BankingAction.CLOSE_ACCOUNT);
        assertFalse(savingsAccount.isAccountActive());
    }

    @Test
    public void testReopenAccount(){
        SavingsAccount savingsAccount = createSavingsAccount(3000L);
        savingsAccount.closeAccount();

        assertFalse(savingsAccount.isAccountActive());
        bankingController.updateAccount(savingsAccount, BankingAction.REOPEN_ACCOUNT);

        assertTrue(savingsAccount.isAccountActive());
    }

    @Test
    public void testReopenOpenAccount(){
        SavingsAccount savingsAccount = createSavingsAccount(3000L);
        assertTrue(savingsAccount.isAccountActive());
        bankingController.updateAccount(savingsAccount, BankingAction.REOPEN_ACCOUNT);
        assertTrue(savingsAccount.isAccountActive());
    }

    @Test(expected = BankAccountException.class)
    public void testReopenAccountFail(){
        SavingsAccount savingsAccount = createSavingsAccount(0L);
        savingsAccount.closeAccount();
        bankingController.updateAccount(savingsAccount, BankingAction.REOPEN_ACCOUNT);
    }

    private SavingsAccount createSavingsAccount(long balance) {
        return new SavingsAccount(balance, BankAccount.SAVINGS_CREDIT_INTEREST_RATE, BankAccount.SAVINGS_DEBIT_INTEREST_RATE);
    }

}
