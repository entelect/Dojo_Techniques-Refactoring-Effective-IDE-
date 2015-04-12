package za.co.entelect.refactoring_final.service;

import za.co.entelect.refactoring_final.domain.BankAccount;

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