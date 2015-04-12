package za.co.entelect.refactoring2.controller;

import org.junit.Test;
import za.co.entelect.refactoring2.domain.BankAccount;
import za.co.entelect.refactoring2.domain.BankingAction;
import za.co.entelect.refactoring2.domain.ChequeAccount;
import za.co.entelect.refactoring2.domain.SavingsAccount;
import za.co.entelect.refactoring2.exception.BankAccountException;
import za.co.entelect.refactoring2.service.ImageService;

import static junit.framework.Assert.*;

public class BankingControllerTest {

    public static final long INITIAL_BALANCE = 3000L;

    private BankingController bankingController = new BankingController();

    @Test
    public void testFetchImage(){
        assertNotNull(bankingController.fetchImage(ImageService.DEFAULT_IMAGE));
    }

    @Test
    public void testUploadImage(){
        assertNotNull(bankingController.uploadImage("1", new byte[1]));
    }

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
        assertEquals(savingsAccount.getBalanceInCents(), INITIAL_BALANCE - BankAccount.SAVINGS_ACCOUNT_FEE);
    }

    private SavingsAccount createSavingsAccount(long balance) {
        return new SavingsAccount(balance, BankAccount.SAVINGS_CREDIT_INTEREST_RATE, BankAccount.SAVINGS_DEBIT_INTEREST_RATE, BankAccount.SAVINGS_ACCOUNT_FEE);
    }

    private ChequeAccount createChequeAccount(long balance) {
        return new ChequeAccount(balance, BankAccount.CHEQUE_CREDIT_INTEREST_RATE, BankAccount.CHEQUE_DEBIT_INTEREST_RATE, BankAccount.CHEQUE_ACCOUNT_FEE);
    }
}