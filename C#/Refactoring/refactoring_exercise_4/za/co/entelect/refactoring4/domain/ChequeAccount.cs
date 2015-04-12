namespace refactoring_exercise_4.za.co.entelect.refactoring4.domain
{

    public class ChequeAccount : BankAccount
    {
        public const double ChequeCreditInterestRate = 0.04D;
        public const double ChequeDebitInterestRate = 0.12D;
        public const long ChequeAccountFee = 1400;
       

        public ChequeAccount(long balanceInCents) :
            base(balanceInCents, ChequeCreditInterestRate, ChequeDebitInterestRate, ChequeAccountFee)
        {
        }

        public override void CalculateBalance(long amountInCents)
        {
            base.UpdateBalance(amountInCents);
        }

        public override void CalculateInterest()
        {
            if (this.BalanceInCents < 0)
            {
                this.UpdateBalance((long) (this.BalanceInCents*this.DebitInterestRate));
            }
            else
            {
                this.UpdateBalance((long) (this.BalanceInCents*this.CreditInterestsRate));
            }
        }

        public override AccountType GetAccountType()
        {
            return AccountType.Cheque;
        }
    }
}