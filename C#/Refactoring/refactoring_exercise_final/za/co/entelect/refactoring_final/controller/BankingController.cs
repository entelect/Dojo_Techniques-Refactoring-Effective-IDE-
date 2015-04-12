using System;
using System.Collections.Generic;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.exception;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.za.co.entelect.refactoring_final.controller
{

    public class BankingController
    {
        public static void Main(String[] args)
        {

        }

        private const long AccountReopenFeeCents = 2000;

        private BankingService _bankingService = new BankingService();

        private readonly Dictionary<BankingAction, IAccountStrategy> _bankingActionStrategies = new Dictionary<BankingAction, IAccountStrategy>();

        public BankingController()
        {
            _bankingActionStrategies.Add(BankingAction.RecalculateBalance, new RecalculateBalanceStrategy());
            _bankingActionStrategies.Add(BankingAction.CloseAccount, new CloseAccountStrategy());
            _bankingActionStrategies.Add(BankingAction.ReopenAccount, new ReopenAccountStrategy());
            _bankingActionStrategies.Add(BankingAction.ChargeAccountFee, new ChargeAccountFeeStrategy());
        }

        public void UpdateAccount(BankAccount bankAccount, BankingAction bankingAction)
        {
            _bankingActionStrategies[bankingAction].UpdateAccount(bankAccount);
        }

        private interface IAccountStrategy
        {
            void UpdateAccount(BankAccount bankAccount);
        }

        private class RecalculateBalanceStrategy : IAccountStrategy
        {

            public void UpdateAccount(BankAccount bankAccount)
            {
                bankAccount.CalculateInterest();
            }
        }

        private class CloseAccountStrategy : IAccountStrategy
        {
            public void UpdateAccount(BankAccount bankAccount)
            {
                if (bankAccount.IsAccountActive)
                {
                    bankAccount.CloseAccount();
                }
            }
        }

        private class ReopenAccountStrategy : IAccountStrategy
        {
            public void UpdateAccount(BankAccount bankAccount)
            {
                if (!bankAccount.IsAccountActive)
                {
                    if (AccountType.Cheque != bankAccount.GetAccountType() &&
                        bankAccount.BalanceInCents - AccountReopenFeeCents < 0)
                    {
                        throw new BankAccountException("Account does not contain a balance to debit the reopen fee");
                    }
                    else
                    {
                        bankAccount.UpdateBalance(-AccountReopenFeeCents);
                        bankAccount.ReopenAccount();
                    }
                }
            }
        }

        private class ChargeAccountFeeStrategy : IAccountStrategy
        {
            public void UpdateAccount(BankAccount bankAccount)
            {
                bankAccount.CalculateBalance(-bankAccount.FeeInCents);
            }
        }
    }
}