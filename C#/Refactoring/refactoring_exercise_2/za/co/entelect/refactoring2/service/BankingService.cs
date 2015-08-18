using System.Collections.Generic;
using refactoring_exercise_2.za.co.entelect.refactoring2.domain;
using refactoring_exercise_2.za.co.entelect.refactoring2.exception;

namespace refactoring_exercise_2.za.co.entelect.refactoring2.service
{
   /**
     *
     * Exercise 2:
     *
     * After the refactoring of exercise 1 we are still left with the following smells
     *
     * 1. Feature envy : The class is too tightly coupled with the internals of the BankAccount
     *
     */
    public class BankingService
    {

         private readonly List<BankAccount> _bankAccounts = new List<BankAccount>();

        public int CountBanksAccounts()
        {
            return _bankAccounts.Count;
        }

        public void AddBankAccount(BankAccount bankAccount)
        {
            _bankAccounts.Add(bankAccount);
        }

        public void CalculateInterest(BankAccount bankAccount)
        {
            if (IsSavingsAccount(bankAccount) || IsMoneyMaketAccount(bankAccount))
            {
                CalculateNonCreditAccountInterest(bankAccount);
            }

            if (AccountType.Cheque == bankAccount.GetAccountType())
            {
                CalculateCreditAccountInterest(bankAccount);
            }
        }

        public void CalculateBalance(BankAccount bankAccount, long amountInCents)
        {
            if (IsSavingsAccount(bankAccount) || IsMoneyMaketAccount(bankAccount))
            {
                UpdateNonCreditAccountBalance(bankAccount, amountInCents);
            }

            //update the balance for a cheque account
            if (IsChequeAccount(bankAccount))
            {
                bankAccount.UpdateBalance(amountInCents);
            }            
        }

        private void UpdateNonCreditAccountBalance(BankAccount bankAccount, long amountInCents)
        {
            HasSufficientFunds(bankAccount, amountInCents);
            bankAccount.UpdateBalance(amountInCents);
        }

        private void HasSufficientFunds(BankAccount bankAccount, long amountInCents)
        {
            if (bankAccount.BalanceInCents + amountInCents < 0)
            {
                throw new BankAccountException("Insufficient funds");
            }
        }

        private void CalculateCreditAccountInterest(BankAccount bankAccount)
        {
            if (bankAccount.BalanceInCents < 0)
            {
                bankAccount.UpdateBalance((long) (bankAccount.BalanceInCents*bankAccount.DebitInterestRate));
            }
            else
            {
                bankAccount.UpdateBalance((long) (bankAccount.BalanceInCents*bankAccount.CreditInterestsRate));
            }
        }

        private void CalculateNonCreditAccountInterest(BankAccount bankAccount)
        {
            HasNegativeBalance(bankAccount);
            bankAccount.UpdateBalance((long) (bankAccount.BalanceInCents*bankAccount.CreditInterestsRate));
        }

        private void HasNegativeBalance(BankAccount bankAccount)
        {
            if (bankAccount.BalanceInCents < 0)
            {
                throw new BankAccountException("Negative balance not allowed");
            }
        }

        private bool IsMoneyMaketAccount(BankAccount bankAccount)
        {
            return AccountType.MoneyMarket == bankAccount.GetAccountType();
        }

        private bool IsChequeAccount(BankAccount bankAccount)
        {
            return AccountType.Cheque == bankAccount.GetAccountType();
        }

        private bool IsSavingsAccount(BankAccount bankAccount)
        {
            return AccountType.Savings == bankAccount.GetAccountType();
        }
    }
    
}