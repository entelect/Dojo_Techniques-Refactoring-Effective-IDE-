package za.co.entlect.refactoring4.service;

import org.junit.Test;
import za.co.entelect.refactoring4.domain.BankAccount;
import za.co.entelect.refactoring4.domain.SavingsAccount;
import za.co.entelect.refactoring4.service.BankingService;

import static junit.framework.Assert.assertEquals;

public class BankingServiceTest {

    private BankingService bankingService = new BankingService();

    @Test
    public void testAddBankAccount(){
        SavingsAccount savingsAccount = new SavingsAccount(1000L,
                BankAccount.SAVINGS_CREDIT_INTEREST_RATE, BankAccount.SAVINGS_DEBIT_INTEREST_RATE, BankAccount.SAVINGS_ACCOUNT_FEE);
        assertEquals(0, bankingService.countBanksAccounts());
        bankingService.addBankAccount(savingsAccount);
        assertEquals(1, bankingService.countBanksAccounts());
    }


}
