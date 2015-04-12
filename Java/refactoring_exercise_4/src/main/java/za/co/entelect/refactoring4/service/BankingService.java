package za.co.entelect.refactoring4.service;

import za.co.entelect.refactoring4.domain.BankAccount;

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