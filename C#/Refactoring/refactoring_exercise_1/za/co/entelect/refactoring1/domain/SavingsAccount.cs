namespace refactoring_exercise_1.za.co.entelect.refactoring1.domain
{
    public class SavingsAccount : BankAccount
    {

        public SavingsAccount(long balanceInCents, double creditInterestsRate, double debitInterestRate, long accountFee) :
            base(balanceInCents, creditInterestsRate, debitInterestRate, accountFee)
        {

        }

        public override AccountType GetAccountType()
        {
            return AccountType.Savings;
        }

    }
}