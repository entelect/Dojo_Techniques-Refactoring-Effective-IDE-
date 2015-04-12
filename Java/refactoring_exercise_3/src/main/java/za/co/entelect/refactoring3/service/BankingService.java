package za.co.entelect.refactoring3.service;

import za.co.entelect.refactoring3.domain.BankAccount;

import java.util.ArrayList;
import java.util.List;


public class BankingService {

    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public int countBanksAccounts() {
        return bankAccounts.size();
    }

    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

}