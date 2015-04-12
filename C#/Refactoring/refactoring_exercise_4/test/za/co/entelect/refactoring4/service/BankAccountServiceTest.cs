using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_4.za.co.entelect.refactoring4.domain;
using refactoring_exercise_4.za.co.entelect.refactoring4.service;

namespace refactoring_exercise_4.test.za.co.entelect.refactoring4.service
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
