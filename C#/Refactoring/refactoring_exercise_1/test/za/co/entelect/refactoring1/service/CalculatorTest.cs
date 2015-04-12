
using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_1.za.co.entelect.refactoring1.domain;
using refactoring_exercise_1.za.co.entelect.refactoring1.exception;
using refactoring_exercise_1.za.co.entelect.refactoring1.service;

namespace refactoring_exercise_1.test.za.co.entelect.refactoring1.service
{
    [TestFixture]
    public class CalculatorTest {

        public const long InitialBalance = 1000L;

        private BankAccount _savingsAccount;
        private BankAccount _moneyMarketAccount;
        private BankAccount _chequeAccount;

        private readonly Calculator _calculator = new Calculator();
        private BankAccount[] _bankAccounts;

        [Test]
        public void TestAddBankAccount(){
            SavingsAccount savingsAccount = new SavingsAccount(1000L,
                BankAccount.SavingsCreditInterestRate, BankAccount.SavingsDebitInterestRate, BankAccount.SavingsAccountFee);
            Assert.That(_calculator.CountBanksAccounts(), new EqualConstraint(0));
            _calculator.AddBankAccount(savingsAccount);
            Assert.That(_calculator.CountBanksAccounts(), new EqualConstraint(1));
        }

        [Test]
        public void TestPositiveBalance() {
            CreateTestAccounts(InitialBalance);

            _calculator.Calculate(_savingsAccount);
            Assert.That(_savingsAccount.BalanceInCents, new EqualConstraint(1050));

            _calculator.Calculate(_chequeAccount);
            Assert.That(_chequeAccount.BalanceInCents, new EqualConstraint(1040));

            _calculator.Calculate(_moneyMarketAccount);
            Assert.That(_moneyMarketAccount.BalanceInCents, new EqualConstraint(1100));
        }

        [Test]
        public void TestNegativeBalanceChequeAccount() {
            CreateTestAccounts(-500L);

            _calculator.Calculate(_chequeAccount);
            Assert.That(_chequeAccount.BalanceInCents, new EqualConstraint(-560));
        }

        [Test]
        [ExpectedException( typeof (BankAccountException))]
        public void TestNegativeBalanceSavingsAccount() {
            CreateTestAccounts(-500L);
            _calculator.Calculate(_savingsAccount);
        }

        [Test]
        [ExpectedException( typeof (BankAccountException))]
        public void TestNegativeBalanceMoneyMarketAccount() {
            CreateTestAccounts(-500L);
            _calculator.Calculate(_moneyMarketAccount);
        }

        [Test]
        public void TestUpdateBalance()
        {
            CreateTestAccounts(InitialBalance);

            foreach (BankAccount account in _bankAccounts)
            {
                _calculator.CalculateBalance(account, -500);
                Assert.That(account.BalanceInCents, new EqualConstraint(500));

                _calculator.CalculateBalance(account, 1000);
                Assert.That(account.BalanceInCents, new EqualConstraint(1500));
            }
        }

        [Test]
        [ExpectedException( typeof (BankAccountException))]
        public void TestUpdateSavingsAccountBalanceFail(){
            CreateTestAccounts(InitialBalance);
            _calculator.CalculateBalance(_savingsAccount, -InitialBalance * 2);
        }

        [Test]
        [ExpectedException(typeof(BankAccountException))]
        public void TestUpdateMoneyMarketAccountBalanceFail(){
            CreateTestAccounts(InitialBalance);
            _calculator.CalculateBalance(_moneyMarketAccount, -InitialBalance * 2);
        }

        private void CreateTestAccounts(long initialBalance) {
            _savingsAccount = new SavingsAccount(initialBalance,
                BankAccount.SavingsCreditInterestRate, BankAccount.SavingsDebitInterestRate, BankAccount.SavingsAccountFee);

            _chequeAccount = new ChequeAccount(initialBalance,
                BankAccount.ChequeCreditInterestRate, BankAccount.ChequeDebitInterestRate, BankAccount.ChequeAccountFee);

            _moneyMarketAccount = new MoneyMarketAccount(initialBalance,
                BankAccount.MoneyMarketCreditInterestRate, BankAccount.MoneyMarketDebitInterestRate, BankAccount.MoneyMarketAccountFee);

            _bankAccounts = new[]{_savingsAccount, _chequeAccount, _moneyMarketAccount};
        }
    }
}
