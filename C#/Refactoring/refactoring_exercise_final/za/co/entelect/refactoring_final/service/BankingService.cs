using System.Collections.Generic;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;

namespace refactoring_exercise_final.za.co.entelect.refactoring_final.service
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