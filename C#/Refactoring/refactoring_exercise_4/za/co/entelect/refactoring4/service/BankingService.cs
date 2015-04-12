using System.Collections.Generic;
using refactoring_exercise_4.za.co.entelect.refactoring4.domain;

namespace refactoring_exercise_4.za.co.entelect.refactoring4.service
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