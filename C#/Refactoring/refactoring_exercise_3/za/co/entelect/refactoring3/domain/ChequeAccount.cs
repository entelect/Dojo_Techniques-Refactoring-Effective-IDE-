namespace refactoring_exercise_3.za.co.entelect.refactoring3.domain
{

    public class ChequeAccount : BankAccount
    {

        public ChequeAccount(long balanceInCents, double creditInterestsRate, double debitInterestRate, long accountFee) :
            base(balanceInCents, creditInterestsRate, debitInterestRate, accountFee)
        {
        }

        public override AccountType GetAccountType()
        {
            return AccountType.Cheque;
        }
    }
}