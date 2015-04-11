package za.co.entlect.refactoring1;

import org.junit.Test;
import za.co.entelect.refactoring1.AccountBalanceCalculator;
import za.co.entelect.refactoring1.AccountType;
import za.co.entelect.refactoring1.BankAccount;

import static junit.framework.Assert.assertEquals;

public class AccountBalanceCalculatorTest {

    private BankAccount savingsAccount;
    private BankAccount moneyMarketAccount;
    private BankAccount chequeAccount;

    private AccountBalanceCalculator calculator = new AccountBalanceCalculator();

    @Test
    public void testPositiveBalance() {
        createTestAccounts(1000L);

        calculator.calculateNewBalance(savingsAccount);
        assertEquals(1050, savingsAccount.getBalanceInCents());

        calculator.calculateNewBalance(chequeAccount);
        assertEquals(1040, chequeAccount.getBalanceInCents());

        calculator.calculateNewBalance(moneyMarketAccount);
        assertEquals(1100, moneyMarketAccount.getBalanceInCents());
    }

    @Test
    public void testNegativeBalanceChequeAccount() {
        createTestAccounts(-500L);

        calculator.calculateNewBalance(chequeAccount);
        assertEquals(-560, chequeAccount.getBalanceInCents());
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceSavingsAccount() {
        createTestAccounts(-500L);
        calculator.calculateNewBalance(savingsAccount);
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceMoneyMarketAccount() {
        createTestAccounts(-500L);
        calculator.calculateNewBalance(moneyMarketAccount);
    }

    private void createTestAccounts(long initialBalance) {
        savingsAccount = new BankAccount(AccountType.SAVINGS, initialBalance,
                BankAccount.SAVINGS_CREDIT_INTEREST_RATE, BankAccount.SAVINGS_DEBIT_INTEREST_RATE);

        chequeAccount = new BankAccount(AccountType.CHEQUE, initialBalance,
                BankAccount.CHEQUE_CREDIT_INTEREST_RATE, BankAccount.CHEQUE_MARKET_DEBIT_INTEREST_RATE);

        moneyMarketAccount = new BankAccount(AccountType.MONEY_MARKET, initialBalance,
                BankAccount.MONEY_MARKET_CREDIT_INTEREST_RATE, BankAccount.MONEY_MARKET_DEBIT_INTEREST_RATE);
    }
}
