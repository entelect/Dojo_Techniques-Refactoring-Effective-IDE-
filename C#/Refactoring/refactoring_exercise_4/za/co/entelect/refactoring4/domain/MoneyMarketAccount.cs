namespace refactoring_exercise_4.za.co.entelect.refactoring4.domain
{

    public class MoneyMarketAccount : BankAccount
    {
        public const double MoneyMarketCreditInterestRate = 0.1D;
        public const double MoneyMarketDebitInterestRate = double.NaN;
        public const long MoneyMarketAccountFee = 1200;


        public MoneyMarketAccount(long balanceInCents) :
            base(balanceInCents, MoneyMarketCreditInterestRate, MoneyMarketDebitInterestRate, MoneyMarketAccountFee)
        {

        }

        public override void CalculateBalance(long amountInCents)
        {
            this.HasSufficientFunds(amountInCents);
            this.UpdateBalance(amountInCents);
        }

        public override void CalculateInterest()
        {
            this.HasNegativeBalance();
            this.UpdateBalance((long) (this.BalanceInCents*this.CreditInterestsRate));
        }

        public override AccountType GetAccountType()
        {
            return AccountType.MoneyMarket;
        }
    }
}