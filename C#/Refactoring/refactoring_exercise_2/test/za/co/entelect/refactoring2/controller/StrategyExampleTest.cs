using NUnit.Framework;
using NUnit.Framework.Constraints;

namespace refactoring_exercise_2.test.za.co.entelect.refactoring2.controller
{
    [TestFixture]
    public class StrategyExampleTest {

        private StrategyExample strategyExample = new StrategyExample();

        [Test]
        public void TestCalculates(){
            Assert.That(strategyExample.Calculate(StrategyExample.Add, 10, 2), new EqualConstraint(12));
            Assert.That(strategyExample.Calculate(StrategyExample.Subtract, 10, 2), new EqualConstraint(8));
            Assert.That(strategyExample.Calculate(StrategyExample.Multiply, 10, 2), new EqualConstraint(20));
            Assert.That(strategyExample.Calculate(StrategyExample.Divide, 10, 2), new EqualConstraint(5));
        }
    }
}