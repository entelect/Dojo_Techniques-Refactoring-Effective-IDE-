using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.exception;

namespace refactoring_exercise_final.test.za.co.entelect.refactoring_final.domain
{
    class BankAccountTest
    {
        public const long InitialBalance = 1000L;

        private BankAccount _savingsAccount;
        private BankAccount _moneyMarketAccount;
        private BankAccount _chequeAccount;

        private BankAccount[] _bankAccounts;

        [Test]
        public void TestPositiveBalance()
        {
            CreateTestAccounts(InitialBalance);

            _savingsAccount.CalculateInterest();
            Assert.That(_savingsAccount.BalanceInCents, new EqualConstraint(1050));

            _chequeAccount.CalculateInterest();
            Assert.That(_chequeAccount.BalanceInCents, new EqualConstraint(1040));

            _moneyMarketAccount.CalculateInterest();
            Assert.That(_moneyMarketAccount.BalanceInCents, new EqualConstraint(1100));
        }

        [Test]
        public void TestNegativeBalanceChequeAccount()
        {
            CreateTestAccounts(-500L);

            _chequeAccount.CalculateInterest();
            Assert.That(_chequeAccount.BalanceInCents, new EqualConstraint(-560));
        }

        [Test]
        public void TestNegativeBalanceSavingsAccount()
        {
            CreateTestAccounts(-500L);
            Assert.Throws<BankAccountException>(() => _savingsAccount.CalculateInterest());
        }

        [Test]
        public void TestNegativeBalanceMoneyMarketAccount()
        {
            CreateTestAccounts(-500L);
            Assert.Throws<BankAccountException>(() => _moneyMarketAccount.CalculateInterest());
        }

        [Test]
        public void TestUpdateBalance()
        {
            CreateTestAccounts(InitialBalance);

            foreach (BankAccount account in _bankAccounts)
            {
                account.CalculateBalance(-500);
                Assert.That(account.BalanceInCents, new EqualConstraint(500));

                account.CalculateBalance(1000);
                Assert.That(account.BalanceInCents, new EqualConstraint(1500));
            }
        }

        [Test]
        public void TestUpdateSavingsAccountBalanceFail()
        {
            CreateTestAccounts(InitialBalance);
            Assert.Throws<BankAccountException>(() => _savingsAccount.CalculateBalance(-InitialBalance * 2));
        }

        [Test]
        public void TestUpdateMoneyMarketAccountBalanceFail()
        {
            CreateTestAccounts(InitialBalance);
            Assert.Throws<BankAccountException>(() => _moneyMarketAccount.CalculateBalance(-InitialBalance * 2));
        }

        private void CreateTestAccounts(long initialBalance)
        {
            _savingsAccount = new SavingsAccount(initialBalance);

            _chequeAccount = new ChequeAccount(initialBalance);

            _moneyMarketAccount = new MoneyMarketAccount(initialBalance);

            _bankAccounts = new[] { _savingsAccount, _chequeAccount, _moneyMarketAccount };
        }
    }
}
