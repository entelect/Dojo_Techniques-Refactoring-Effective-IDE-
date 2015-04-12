package za.co.entlect.refactoring1.service;

import org.junit.Test;
import za.co.entelect.refactoring1.domain.BankAccount;
import za.co.entelect.refactoring1.domain.ChequeAccount;
import za.co.entelect.refactoring1.domain.MoneyMarketAccount;
import za.co.entelect.refactoring1.domain.SavingsAccount;
import za.co.entelect.refactoring1.exception.BankAccountException;
import za.co.entelect.refactoring1.service.Calculator;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {

    public static final long INITIAL_BALANCE = 1000L;

    private BankAccount savingsAccount;
    private BankAccount moneyMarketAccount;
    private BankAccount chequeAccount;

    private Calculator calculator = new Calculator();
    private BankAccount[] bankAccounts;

    @Test
    public void testAddBankAccount(){
        createTestAccounts(1000);
        assertEquals(0, calculator.countBanksAccounts());
        calculator.addBankAccount(chequeAccount);
        assertEquals(1, calculator.countBanksAccounts());
    }

    @Test
    public void testPositiveBalance() {
        createTestAccounts(INITIAL_BALANCE);

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

    @Test
    public void testUpdateBalance(){
        createTestAccounts(INITIAL_BALANCE);

        for (int i = 0; i < bankAccounts.length; i++) {
            calculator.updateBalance(bankAccounts[i], -500);
            assertEquals(500, bankAccounts[i].getBalanceInCents());

            calculator.updateBalance(bankAccounts[i], 1000);
            assertEquals(1500, bankAccounts[i].getBalanceInCents());
        }
    }

    @Test(expected = BankAccountException.class)
    public void testUpdateSavingsAccountBalanceFail(){
        createTestAccounts(INITIAL_BALANCE);
        calculator.updateBalance(savingsAccount, -INITIAL_BALANCE*2);
    }

    @Test(expected = BankAccountException.class)
    public void testUpdateMoneyMarketAccountBalanceFail(){
        createTestAccounts(INITIAL_BALANCE);
        calculator.updateBalance(moneyMarketAccount, -INITIAL_BALANCE*2);
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
