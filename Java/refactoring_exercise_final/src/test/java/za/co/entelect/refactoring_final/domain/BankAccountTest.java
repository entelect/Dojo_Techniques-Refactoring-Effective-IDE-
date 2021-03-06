package za.co.entelect.refactoring_final.domain;

import org.junit.Test;
import za.co.entelect.refactoring_final.exception.BankAccountException;

import static junit.framework.Assert.assertEquals;

public class BankAccountTest {

    public static final long INITIAL_BALANCE = 1000L;

    private BankAccount savingsAccount;
    private BankAccount moneyMarketAccount;
    private BankAccount chequeAccount;

    private BankAccount[] bankAccounts;

    @Test
    public void testPositiveBalance() {
        createTestAccounts(INITIAL_BALANCE);

        savingsAccount.calculateInterest();
        assertEquals(1050, savingsAccount.getBalanceInCents());

        chequeAccount.calculateInterest();
        assertEquals(1040, chequeAccount.getBalanceInCents());

        moneyMarketAccount.calculateInterest();
        assertEquals(1100, moneyMarketAccount.getBalanceInCents());
    }

    @Test
    public void testNegativeBalanceChequeAccount() {
        createTestAccounts(-500L);

        chequeAccount.calculateInterest();
        assertEquals(-560, chequeAccount.getBalanceInCents());
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceSavingsAccount() {
        createTestAccounts(-500L);
        savingsAccount.calculateInterest();
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceMoneyMarketAccount() {
        createTestAccounts(-500L);
        moneyMarketAccount.calculateInterest();
    }

    @Test
    public void testUpdateBalance(){
        createTestAccounts(INITIAL_BALANCE);

        for (int i = 0; i < bankAccounts.length; i++) {
            bankAccounts[i].calculateBalance(-500);
            assertEquals(500, bankAccounts[i].getBalanceInCents());

            bankAccounts[i].calculateBalance(1000);
            assertEquals(1500, bankAccounts[i].getBalanceInCents());
        }
    }

    @Test(expected = BankAccountException.class)
    public void testUpdateSavingsAccountBalanceFail(){
        createTestAccounts(INITIAL_BALANCE);
        savingsAccount.calculateBalance(-INITIAL_BALANCE * 2);
    }

    @Test(expected = BankAccountException.class)
    public void testUpdateMoneyMarketAccountBalanceFail(){
        createTestAccounts(INITIAL_BALANCE);
        moneyMarketAccount.calculateBalance(-INITIAL_BALANCE * 2);
    }

    private void createTestAccounts(long initialBalance) {
        savingsAccount = new SavingsAccount(initialBalance
        );

        chequeAccount = new ChequeAccount(initialBalance
        );

        moneyMarketAccount = new MoneyMarketAccount(initialBalance
        );

        bankAccounts = new BankAccount[]{savingsAccount, chequeAccount, moneyMarketAccount};
    }
}
