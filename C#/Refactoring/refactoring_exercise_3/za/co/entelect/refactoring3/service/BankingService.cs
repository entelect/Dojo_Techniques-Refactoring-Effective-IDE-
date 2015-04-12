using System.Collections.Generic;
using refactoring_exercise_3.za.co.entelect.refactoring3.domain;
using refactoring_exercise_3.za.co.entelect.refactoring3.exception;

namespace refactoring_exercise_3.za.co.entelect.refactoring3.service
{

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
    }
    
}