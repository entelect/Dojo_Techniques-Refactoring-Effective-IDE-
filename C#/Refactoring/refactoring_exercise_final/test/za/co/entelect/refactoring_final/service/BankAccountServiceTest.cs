using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.test.za.co.entelect.refactoring_final.service
{
    [TestFixture]
    public class BankAccountServiceTest {

        private readonly BankingService _bankingService = new  BankingService();

        [Test]
        public void TestAddBankAccount()
        {
            SavingsAccount savingsAccount = new SavingsAccount(1000L);
            Assert.That(_bankingService.CountBanksAccounts(), new EqualConstraint(0));
            _bankingService.AddBankAccount(savingsAccount);
            Assert.That(_bankingService.CountBanksAccounts(), new EqualConstraint(1));
        }
    }
}
