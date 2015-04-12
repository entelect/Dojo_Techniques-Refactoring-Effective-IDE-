using refactoring_exercise_3.za.co.entelect.refactoring3.exception;

/* Exercise 3:
 *
 * This exercise demonstrates the following code smells
 *
 * 1. Shotgun surgery/Inappropriate Intimacy : The constants should exist in the appropriate classes as well as the methods to debit the account and calculate interest
 *
 */
namespace refactoring_exercise_3.za.co.entelect.refactoring3.domain
{
    /* Exercise 3:
     *
     * This exercise demonstrates the following code smells
     *
     * 1. Shotgun surgery/Inappropriate Intimacy : The constants should exist in the appropriate classes as well as the methods to debit the account and calculate interest
     *
     */
    public abstract class BankAccount
    {

        public const double SavingsCreditInterestRate = 0.05D;
        public const double SavingsDebitInterestRate = double.NaN;
        public const long SavingsAccountFee = 1000;

        public const double MoneyMarketCreditInterestRate = 0.1D;
        public const double MoneyMarketDebitInterestRate = double.NaN;
        public const long MoneyMarketAccountFee = 1200;

        public const double ChequeCreditInterestRate = 0.04D;
        public const double ChequeDebitInterestRate = 0.12D;
        public const long ChequeAccountFee = 1400;

        private long _balanceInCents;
        private bool _accountActive = true;

        public BankAccount(long balanceInCents, double creditInterestsRate, double debitInterestRate, long fee)
        {
            this._balanceInCents = balanceInCents;
            this.CreditInterestsRate = creditInterestsRate;
            this.DebitInterestRate = debitInterestRate;
            this.FeeInCents = fee;
        }

        public abstract AccountType GetAccountType();

        public void CalculateBalance(long amountInCents)
        {
            if (IsSavingsAccount() || IsMoneyMaketAccount())
            {
                UpdateNonCreditAccountBalance(amountInCents);
            }

            //update the balance for a cheque account
            if (IsChequeAccount())
            {
                UpdateBalance(amountInCents);
            }
        }

        public void CalculateInterest()
        {
            if (IsSavingsAccount() || IsMoneyMaketAccount())
            {
                CalculateNonCreditAccountInterest();
            }

            if (AccountType.Cheque == GetAccountType())
            {
                CalculateCreditAccountInterest();
            }
        }

        public long BalanceInCents
        {
            get { return _balanceInCents; }
        }

        public void UpdateBalance(long balanceInCents)
        {
            this._balanceInCents += balanceInCents;
        }

        public double CreditInterestsRate { get; private set; }

        public double DebitInterestRate { get; private set; }

        public bool IsAccountActive()
        {
            return _accountActive;
        }

        public void CloseAccount()
        {
            this._accountActive = false;
        }

        public void ReopenAccount()
        {
            this._accountActive = true;
        }

        public long FeeInCents { get; private set; }

        public bool IsMoneyMaketAccount()
        {
            return AccountType.MoneyMarket == GetAccountType();
        }

        public bool IsChequeAccount()
        {
            return AccountType.Cheque == GetAccountType();
        }

        public bool IsSavingsAccount()
        {
            return AccountType.Savings == GetAccountType();
        }

        public void HasNegatvieBalance()
        {
            if (BalanceInCents < 0)
            {
                throw new BankAccountException("Negative balance not allowed");
            }
        }

        public void CalculateNonCreditAccountInterest()
        {
            HasNegatvieBalance();
            UpdateBalance((long) (BalanceInCents*CreditInterestsRate));
        }

        public void CalculateCreditAccountInterest()
        {
            if (BalanceInCents < 0)
            {
                UpdateBalance((long) (BalanceInCents*DebitInterestRate));
            }
            else
            {
                UpdateBalance((long) (BalanceInCents*CreditInterestsRate));
            }
        }

        public void HasSufficientFunds(long amountInCents)
        {
            if (BalanceInCents + amountInCents < 0)
            {
                throw new BankAccountException("Insufficient funds");
            }
        }

        public void UpdateNonCreditAccountBalance(long amountInCents)
        {
            HasSufficientFunds(amountInCents);
            UpdateBalance(amountInCents);
        }

       
    }
}