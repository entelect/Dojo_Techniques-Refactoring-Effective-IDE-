

using System.Collections.Generic;
using refactoring_exercise_1.za.co.entelect.refactoring1.domain;
using refactoring_exercise_1.za.co.entelect.refactoring1.exception;

namespace refactoring_exercise_1.za.co.entelect.refactoring1.service
{


    /**
     * Exercise 1:
     *
     * This exercise demonstrates the following code smells
     * 1. Bad Naming
     * 2. Comments
     * 4. Duplication
     * 3. Long Method
     */
    // this class calculates the updated balance for bank accounts
    public class Calculator
    {

        private List<BankAccount> bankAccounts = new List<BankAccount>();

        public int CountBanksAccounts()
        {
            return bankAccounts.Count;
        }

        public void AddBankAccount(BankAccount bankAccount)
        {
            bankAccounts.Add(bankAccount);
        }

        public void Calculate(BankAccount bankAccount)
        {
            //calculate the balance for a savings account
            if (AccountType.Savings == bankAccount.GetAccountType())
            {// side note: it's always better to place the constant on the left, this prevents NullPointerExceptions
                //check has negative balance
                if (bankAccount.BalanceInCents < 0)
                {
                    throw new BankAccountException("Negative balance not allowed");
                }
                bankAccount.UpdateBalance((long)(bankAccount.BalanceInCents * bankAccount.GetCreditInterestsRate()));
            }

            //calculate the balance for a cheque account
            if (AccountType.Cheque == bankAccount.GetAccountType())
            {
                if (bankAccount.BalanceInCents < 0)
                {
                    bankAccount.UpdateBalance((long)(bankAccount.BalanceInCents * bankAccount.GetDebitInterestRate()));
                }
                else
                {
                    bankAccount.UpdateBalance((long)(bankAccount.BalanceInCents * bankAccount.GetCreditInterestsRate()));
                }
            }

            //calculate the balance for a cheque money market account
            if (AccountType.MoneyMarket == bankAccount.GetAccountType())
            {
                //check has negative balance
                if (bankAccount.BalanceInCents < 0)
                {
                    throw new BankAccountException("Negative balance not allowed");
                }
                bankAccount.UpdateBalance((long)(bankAccount.BalanceInCents * bankAccount.GetCreditInterestsRate()));
            }
        }

        public void CalculateBalance(BankAccount bankAccount, long amountInCents)
        {
            //update the balance for a savings account
            if (AccountType.Savings == bankAccount.GetAccountType())
            {
                //cannot go into negative balance
                if (bankAccount.BalanceInCents + amountInCents < 0)
                {
                    throw new BankAccountException("Insufficient funds");
                }
                bankAccount.UpdateBalance(amountInCents);
            }

            //update the balance for a cheque account
            if (AccountType.Cheque == bankAccount.GetAccountType())
            {
                bankAccount.UpdateBalance(amountInCents);
            }

            //calculate the balance for a money market account
            if (AccountType.MoneyMarket == bankAccount.GetAccountType())
            {
                //cannot go into negative balance
                if (bankAccount.BalanceInCents + amountInCents < 0)
                {
                    throw new BankAccountException("Insufficient funds");
                }
                bankAccount.UpdateBalance(amountInCents);
            }
        }
    }
}