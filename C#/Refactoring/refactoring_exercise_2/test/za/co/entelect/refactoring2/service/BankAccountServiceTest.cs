using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_2.za.co.entelect.refactoring2.domain;
using refactoring_exercise_2.za.co.entelect.refactoring2.exception;
using refactoring_exercise_2.za.co.entelect.refactoring2.service;

namespace refactoring_exercise_2.test.za.co.entelect.refactoring2.service
{
    [TestFixture]
    public class BankAccountServiceTest {

        public const long InitialBalance = 1000L;

        private BankAccount _savingsAccount;
        private BankAccount _moneyMarketAccount;
        private BankAccount _chequeAccount;

        private readonly BankingService _bankingService = new BankingService();
        private BankAccount[] _bankAccounts;

        [Test]
        public void TestAddBankAccount(){
            SavingsAccount savingsAccount = new SavingsAccount(1000L,
                BankAccount.SavingsCreditInterestRate, BankAccount.SavingsDebitInterestRate, BankAccount.SavingsAccountFee);
            Assert.That(_bankingService.CountBanksAccounts(), new EqualConstraint(0));
            _bankingService.AddBankAccount(savingsAccount);
            Assert.That(_bankingService.CountBanksAccounts(), new EqualConstraint(1));
        }

        [Test]
        public void TestPositiveBalance() {
            CreateTestAccounts(InitialBalance);

            _bankingService.CalculateInterest(_savingsAccount);
            Assert.That(_savingsAccount.BalanceInCents, new EqualConstraint(1050));

            _bankingService.CalculateInterest(_chequeAccount);
            Assert.That(_chequeAccount.BalanceInCents, new EqualConstraint(1040));

            _bankingService.CalculateInterest(_moneyMarketAccount);
            Assert.That(_moneyMarketAccount.BalanceInCents, new EqualConstraint(1100));
        }

        [Test]
        public void TestNegativeBalanceChequeAccount() {
            CreateTestAccounts(-500L);

            _bankingService.CalculateInterest(_chequeAccount);
            Assert.That(_chequeAccount.BalanceInCents, new EqualConstraint(-560));
        }

        [Test]
        public void TestNegativeBalanceSavingsAccount() {
            CreateTestAccounts(-500L);
            Assert.Throws<BankAccountException>(() => _bankingService.CalculateInterest(_savingsAccount));
        }

        [Test]
        public void TestNegativeBalanceMoneyMarketAccount() {
            CreateTestAccounts(-500L);
            Assert.Throws<BankAccountException>(() => _bankingService.CalculateInterest(_moneyMarketAccount));
        }

        [Test]
        public void TestUpdateBalance()
        {
            CreateTestAccounts(InitialBalance);

            foreach (BankAccount account in _bankAccounts)
            {
                _bankingService.CalculateBalance(account, -500);
                Assert.That(account.BalanceInCents, new EqualConstraint(500));

                _bankingService.CalculateBalance(account, 1000);
                Assert.That(account.BalanceInCents, new EqualConstraint(1500));
            }
        }

        [Test]
        public void TestUpdateSavingsAccountBalanceFail(){
            CreateTestAccounts(InitialBalance);
            Assert.Throws<BankAccountException>(() => _bankingService.CalculateBalance(_savingsAccount, -InitialBalance * 2));
        }

        [Test]
        public void TestUpdateMoneyMarketAccountBalanceFail(){
            CreateTestAccounts(InitialBalance);
            Assert.Throws<BankAccountException>(() => _bankingService.CalculateBalance(_moneyMarketAccount, -InitialBalance * 2));
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
