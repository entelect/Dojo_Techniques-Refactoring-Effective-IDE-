namespace refactoring_exercise_1.za.co.entelect.refactoring1.domain
{

    public abstract class BankAccount
    {

        public const double SAVINGS_CREDIT_INTEREST_RATE = 0.05D;
        public const double SAVINGS_DEBIT_INTEREST_RATE = double.NaN;
        public const long SAVINGS_ACCOUNT_FEE = 1000;

        public const double MONEY_MARKET_CREDIT_INTEREST_RATE = 0.1D;
        public const double MONEY_MARKET_DEBIT_INTEREST_RATE = double.NaN;
        public const long MONEY_MARKET_ACCOUNT_FEE = 1200;

        public const double CHEQUE_CREDIT_INTEREST_RATE = 0.04D;
        public const double CHEQUE_DEBIT_INTEREST_RATE = 0.12D;
        public const long CHEQUE_ACCOUNT_FEE = 1400;

        private readonly double creditInterestsRate;
        private readonly double debitInterestRate;
        private readonly long feeInCents;

        private long _balanceInCents;
        private bool _accountActive = true;

        public BankAccount(long balanceInCents, double creditInterestsRate, double debitInterestRate, long fee)
        {
            this._balanceInCents = balanceInCents;
            this.creditInterestsRate = creditInterestsRate;
            this.debitInterestRate = debitInterestRate;
            this.feeInCents = fee;
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

        public double GetCreditInterestsRate()
        {
            return creditInterestsRate;
        }

        public double GetDebitInterestRate()
        {
            return debitInterestRate;
        }

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

        public long GetFeeInCents()
        {
            return feeInCents;
        }
    }
}