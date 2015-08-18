namespace refactoring_exercise_final.za.co.entelect.refactoring_final.domain
{
    public class SavingsAccount : BankAccount
    {
        public const double SavingsCreditInterestRate = 0.05D;
        public const double SavingsDebitInterestRate = double.NaN;
        public const long SavingsAccountFee = 1000;

        public SavingsAccount(long balanceInCents) :
            base(balanceInCents, SavingsCreditInterestRate, SavingsDebitInterestRate, SavingsAccountFee)
        {

        }

        public override void CalculateBalance(long amountInCents)
        {
            this.HasSufficientFunds(amountInCents);
            this.UpdateBalance(amountInCents);
        }

        public override void CalculateInterest()
        {
            HasNegativeBalance();
            UpdateBalance((long) (BalanceInCents*CreditInterestsRate));
        }

        public override AccountType GetAccountType()
        {
            return AccountType.Savings;
        }

    }
}