using System;
using refactoring_exercise_3.za.co.entelect.refactoring3.domain;
using refactoring_exercise_3.za.co.entelect.refactoring3.exception;
using refactoring_exercise_3.za.co.entelect.refactoring3.service;

namespace refactoring_exercise_3.za.co.entelect.refactoring3.controller
{

    public class BankingController
    {
        public static void Main(String[] args)
        {
            
        }

        private const long AccountReopenFeeCents = 2000;

        private BankingService _bankingService = new BankingService();

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
                    bankAccount.CalculateInterest();
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
                    bankAccount.CalculateBalance(-bankAccount.FeeInCents);
                    break;
            }
        }

    }
}