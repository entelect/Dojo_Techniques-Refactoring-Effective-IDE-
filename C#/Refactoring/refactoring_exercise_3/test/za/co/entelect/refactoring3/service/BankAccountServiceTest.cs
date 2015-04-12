using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_3.za.co.entelect.refactoring3.domain;
using refactoring_exercise_3.za.co.entelect.refactoring3.exception;
using refactoring_exercise_3.za.co.entelect.refactoring3.service;

namespace refactoring_exercise_3.test.za.co.entelect.refactoring3.service
{
    [TestFixture]
    public class BankAccountServiceTest {

        private readonly BankingService _bankingService = new  BankingService();

        [Test]
        public void TestAddBankAccount()
        {
            SavingsAccount savingsAccount = new SavingsAccount(1000L,
                BankAccount.SavingsCreditInterestRate, BankAccount.SavingsDebitInterestRate, BankAccount.SavingsAccountFee);
            Assert.That(_bankingService.CountBanksAccounts(), new EqualConstraint(0));
            _bankingService.AddBankAccount(savingsAccount);
            Assert.That(_bankingService.CountBanksAccounts(), new EqualConstraint(1));
        }
    }
}
