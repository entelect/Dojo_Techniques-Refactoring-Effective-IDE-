namespace refactoring_exercise_2.za.co.entelect.refactoring2.domain
{

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
    }
}