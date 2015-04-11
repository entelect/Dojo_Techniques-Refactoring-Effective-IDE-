package za.co.entlect.refactoring1;

import org.junit.Test;
import za.co.entelect.refactoring1.AccountType;
import za.co.entelect.refactoring1.BankAccount;
import za.co.entelect.refactoring1.Calculator;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {

    private BankAccount savingsAccount;
    private BankAccount moneyMarketAccount;
    private BankAccount chequeAccount;

    private Calculator calculator = new Calculator();

    @Test
    public void testPositiveBalance() {
        createTestAccounts(1000L);

        calculator.calculate(savingsAccount);
        assertEquals(1050, savingsAccount.getBalanceInCents());

        calculator.calculate(chequeAccount);
        assertEquals(1040, chequeAccount.getBalanceInCents());

        calculator.calculate(moneyMarketAccount);
        assertEquals(1100, moneyMarketAccount.getBalanceInCents());
    }

    @Test
    public void testNegativeBalanceChequeAccount() {
        createTestAccounts(-500L);

        calculator.calculate(chequeAccount);
        assertEquals(-560, chequeAccount.getBalanceInCents());
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceSavingsAccount() {
        createTestAccounts(-500L);
        calculator.calculate(savingsAccount);
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeBalanceMoneyMarketAccount() {
        createTestAccounts(-500L);
        calculator.calculate(moneyMarketAccount);
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
