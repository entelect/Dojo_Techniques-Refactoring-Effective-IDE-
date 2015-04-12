using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_2.za.co.entelect.refactoring2.controller;
using refactoring_exercise_2.za.co.entelect.refactoring2.domain;
using refactoring_exercise_2.za.co.entelect.refactoring2.exception;
using refactoring_exercise_2.za.co.entelect.refactoring2.service;

namespace refactoring_exercise_2.test.za.co.entelect.refactoring2.controller
{
    [TestFixture]
    public class BankingControllerTest {

        public const long InitialBalance = 3000L;

        private readonly BankingController bankingController = new BankingController();

        [Test]
        public void TestFetchImage(){
            Assert.NotNull(bankingController.FetchImage(ImageService.DefaultImage));
        }

        [Test]
        public void TestUploadImage(){
            Assert.NotNull((bankingController.UploadImage("1", new byte[1])));
        }

        [Test]
        public void TestRecalculateBalance(){
            SavingsAccount savingsAccount = createSavingsAccount(InitialBalance);
            bankingController.UpdateAccount(savingsAccount, BankingAction.RecalculateBalance);
            Assert.That(savingsAccount.BalanceInCents, new EqualConstraint(3150L));
        }

        [Test]
        public void TestCloseAccount(){
            SavingsAccount savingsAccount = createSavingsAccount(InitialBalance);
            Assert.True(savingsAccount.IsAccountActive());

            bankingController.UpdateAccount(savingsAccount, BankingAction.CloseAccount);
            Assert.False(savingsAccount.IsAccountActive());

            bankingController.UpdateAccount(savingsAccount, BankingAction.CloseAccount);
            Assert.False(savingsAccount.IsAccountActive());
        }

        [Test]
        public void TestReopenAccount(){
            SavingsAccount savingsAccount = createSavingsAccount(InitialBalance);
            savingsAccount.CloseAccount();

            Assert.False(savingsAccount.IsAccountActive());
            bankingController.UpdateAccount(savingsAccount, BankingAction.ReopenAccount);

            Assert.True(savingsAccount.IsAccountActive());
        }

        [Test]
        public void TestReopenOpenSavingsAccount(){
            SavingsAccount savingsAccount = createSavingsAccount(InitialBalance);
            Assert.True(savingsAccount.IsAccountActive());
            bankingController.UpdateAccount(savingsAccount, BankingAction.ReopenAccount);
            Assert.True(savingsAccount.IsAccountActive());
        }

        [Test]
        public void TestReopenChequeAccount(){
            ChequeAccount chequeAccount = createChequeAccount(0);
            Assert.True(chequeAccount.IsAccountActive());
            bankingController.UpdateAccount(chequeAccount, BankingAction.ReopenAccount);
            Assert.True(chequeAccount.IsAccountActive());
        }

        [Test]
        [ExpectedException( typeof (BankAccountException))]
        public void TestReopenAccountFail(){
            SavingsAccount savingsAccount = createSavingsAccount(0L);
            savingsAccount.CloseAccount();
            bankingController.UpdateAccount(savingsAccount, BankingAction.ReopenAccount);
        }

        [Test]
        public void TestChargeAccountFee(){
            SavingsAccount savingsAccount = createSavingsAccount(InitialBalance);
            bankingController.UpdateAccount(savingsAccount, BankingAction.ChargeAccountFee);
            Assert.That(savingsAccount.BalanceInCents, new EqualConstraint(InitialBalance - BankAccount.SavingsAccountFee));
        }

        private SavingsAccount createSavingsAccount(long balance) {
            return new SavingsAccount(balance, BankAccount.SavingsCreditInterestRate, BankAccount.SavingsDebitInterestRate, BankAccount.SavingsAccountFee);
        }

        private ChequeAccount createChequeAccount(long balance) {
            return new ChequeAccount(balance, BankAccount.ChequeCreditInterestRate, BankAccount.ChequeDebitInterestRate, BankAccount.ChequeAccountFee);
        }
    }
}