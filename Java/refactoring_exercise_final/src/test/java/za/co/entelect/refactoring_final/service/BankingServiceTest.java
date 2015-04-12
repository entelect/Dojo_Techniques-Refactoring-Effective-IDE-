package za.co.entelect.refactoring_final.service;

import org.junit.Test;
import za.co.entelect.refactoring_final.domain.SavingsAccount;

import static junit.framework.Assert.assertEquals;

public class BankingServiceTest {

    private BankingService bankingService = new BankingService();

    @Test
    public void testAddBankAccount(){
        SavingsAccount savingsAccount = new SavingsAccount(1000L
        );
        assertEquals(0, bankingService.countBanksAccounts());
        bankingService.addBankAccount(savingsAccount);
        assertEquals(1, bankingService.countBanksAccounts());
    }


}
