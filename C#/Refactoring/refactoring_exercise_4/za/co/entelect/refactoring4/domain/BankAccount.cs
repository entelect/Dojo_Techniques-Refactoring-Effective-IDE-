using refactoring_exercise_4.za.co.entelect.refactoring4.exception;

/* Exercise 3:
 *
 * This exercise demonstrates the following code smells
 *
 * 1. Shotgun surgery/Inappropriate Intimacy : The constants should exist in the appropriate classes as well as the methods to debit the account and calculate interest
 *
 */
namespace refactoring_exercise_4.za.co.entelect.refactoring4.domain
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

        public abstract void CalculateBalance(long amountInCents);

        public abstract void CalculateInterest();

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

        public bool IsAccountActive
        {
            get { return _accountActive; }
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

        public void HasNegatvieBalance()
        {
            if (BalanceInCents < 0)
            {
                throw new BankAccountException("Negative balance not allowed");
            }
        }

        public void HasSufficientFunds(long amountInCents)
        {
            if (BalanceInCents + amountInCents < 0)
            {
                throw new BankAccountException("Insufficient funds");
            }
        }
    }
}