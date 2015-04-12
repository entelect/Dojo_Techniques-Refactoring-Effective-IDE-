using System;
using refactoring_exercise_1.za.co.entelect.refactoring1.domain;
using refactoring_exercise_1.za.co.entelect.refactoring1.exception;
using refactoring_exercise_1.za.co.entelect.refactoring1.service;

namespace refactoring_exercise_1.za.co.entelect.refactoring1.controller
{

    public class BankingController
    {
        public static void Main(String[] args)
        {
            
        }

        private const long AccountReopenFeeCents = 2000;

        private Calculator calculator = new Calculator();

        private ImageServiceDelegate imageServiceDelegate = new ImageServiceDelegate();

        public Image FetchImage(String id)
        {
            return imageServiceDelegate.Fetch(id);
        }

        public Image UploadImage(String id, byte[] data)
        {
            Image image = new Image(id, data);
            imageServiceDelegate.Add(image);
            return image;
        }

        public void UpdateAccount(BankAccount bankAccount, BankingAction bankingAction)
        {
            switch (bankingAction)
            {
                case BankingAction.RecalculateBalance:
                    calculator.Calculate(bankAccount);
                    break;
                case BankingAction.CloseAccount:
                    if (bankAccount.IsAccountActive())
                    {
                        bankAccount.CloseAccount();
                    }
                    break;
                case BankingAction.ReopenAccount:
                    if (!bankAccount.IsAccountActive())
                    {
                        if (AccountType.Cheque != bankAccount.GetAccountType() && bankAccount.BalanceInCents - AccountReopenFeeCents < 0)
                        {
                            throw new BankAccountException("Account does not contain a balance to debit the reopen fee");
                        }
                        else
                        {
                            bankAccount.UpdateBalance(-AccountReopenFeeCents);
                            bankAccount.ReopenAccount();
                        }
                    }
                    break;
                case BankingAction.ChargeAccountFee:
                    calculator.CalculateBalance(bankAccount, -bankAccount.GetFeeInCents());
                    break;
            }
        }

    }
}