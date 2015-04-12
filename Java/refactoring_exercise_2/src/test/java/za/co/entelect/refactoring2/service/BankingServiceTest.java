package za.co.entelect.refactoring2.service;


import org.junit.Test;
import za.co.entelect.refactoring2.domain.BankAccount;
import za.co.entelect.refactoring2.domain.ChequeAccount;
import za.co.entelect.refactoring2.domain.MoneyMarketAccount;
import za.co.entelect.refactoring2.domain.SavingsAccount;
import za.co.entelect.refactoring2.exception.BankAccountException;

import static junit.framework.Assert.assertEquals;

public class BankingServiceTest {

    public static final long INITIAL_BALANCE = 1000L;

    private BankAccount savingsAccount;
    private BankAccount moneyMarketAccount;
    private BankAccount chequeAccount;

    private BankingService bankingService = new BankingService();
    private BankAccount[] bankAccounts;

    @Test
    public void testAddBankAccount(){
        SavingsAccount savingsAccount = new SavingsAccount(1000L,
                BankAccount.SAVINGS_CREDIT_INTEREST_RATE, BankAccount.SAVINGS_DEBIT_INTEREST_RATE, BankAccount.SAVINGS_ACCOUNT_FEE);
        assertEquals(0, bankingService.countBanksAccounts());
        bankingService.addBankAccount(savingsAccount);
        assertEquals(1, bankingService.countBanksAccounts());
    }

    @Test
    public void testPositiveBalance() {
        createTestAccounts(INITIAL_BALANCE);

        bankingService.calculateInterest(savingsAccount);
        assertEquals(1050, savingsAccount.getBalanceInCents());

        bankingService.calculateInterest(chequeAccount);
        assertEquals(1040, chequeAccount.getBalanceInCents());

        bankingService.calculateInterest(moneyMarketAccount);
        assertEquals(1100, moneyMarketAccount.getBalanceInCents());
    }

    @Test
    public void testNegativeBalanceChequeAccount() {
        createTestAccounts(-500L);

        bankingService.calculateInterest(chequeAccount);
        assertEquals(-560, chequeAccount.getBalanceInCents());
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceSavingsAccount() {
        createTestAccounts(-500L);
        bankingService.calculateInterest(savingsAccount);
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceMoneyMarketAccount() {
        createTestAccounts(-500L);
        bankingService.calculateInterest(moneyMarketAccount);
    }

    @Test
    public void testUpdateBalance(){
        createTestAccounts(INITIAL_BALANCE);

        for (int i = 0; i < bankAccounts.length; i++) {
            bankingService.calculateBalance(bankAccounts[i], -500);
            assertEquals(500, bankAccounts[i].getBalanceInCents());

            bankingService.calculateBalance(bankAccounts[i], 1000);
            assertEquals(1500, bankAccounts[i].getBalanceInCents());
        }
    }

    @Test(expected = BankAccountException.class)
    public void testUpdateSavingsAccountBalanceFail(){
        createTestAccounts(INITIAL_BALANCE);
        bankingService.calculateBalance(savingsAccount, -INITIAL_BALANCE * 2);
    }

    @Test(expected = BankAccountException.class)
    public void testUpdateMoneyMarketAccountBalanceFail(){
        createTestAccounts(INITIAL_BALANCE);
        bankingService.calculateBalance(moneyMarketAccount, -INITIAL_BALANCE * 2);
    }

    private void createTestAccounts(long initialBalance) {
        savingsAccount = new SavingsAccount(initialBalance,
                BankAccount.SAVINGS_CREDIT_INTEREST_RATE, BankAccount.SAVINGS_DEBIT_INTEREST_RATE, BankAccount.SAVINGS_ACCOUNT_FEE);

        chequeAccount = new ChequeAccount(initialBalance,
                BankAccount.CHEQUE_CREDIT_INTEREST_RATE, BankAccount.CHEQUE_DEBIT_INTEREST_RATE, BankAccount.CHEQUE_ACCOUNT_FEE);

        moneyMarketAccount = new MoneyMarketAccount(initialBalance,
                BankAccount.MONEY_MARKET_CREDIT_INTEREST_RATE, BankAccount.MONEY_MARKET_DEBIT_INTEREST_RATE, BankAccount.MONEY_MARKET_ACCOUNT_FEE);

        bankAccounts = new BankAccount[]{savingsAccount, chequeAccount, moneyMarketAccount};
    }
}
