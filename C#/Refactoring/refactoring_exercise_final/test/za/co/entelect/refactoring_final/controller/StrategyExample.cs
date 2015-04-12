using System;
using System.Collections.Generic;

namespace refactoring_exercise_final.test.za.co.entelect.refactoring_final.controller
{
    public class StrategyExample
    {

        public const string Add = "ADD";
        public const string Subtract = "SUBTRACT";
        public const string Multiply = "MULTIPLY";
        public const string Divide = "DIVIDE";

        private Dictionary<String, INumericStrategy> numericStrategyMap = new Dictionary<String, INumericStrategy>();

        public StrategyExample()
        {
            numericStrategyMap.Add(Add, new AddStrategy());
            numericStrategyMap.Add(Subtract, new SubtractStrategy());
            numericStrategyMap.Add(Multiply, new MultiplyStrategy());
            numericStrategyMap.Add(Divide, new DivideStrategy());
        }

        public int Calculate(String operation, int a, int b)
        {
            return numericStrategyMap[operation].Calculate(a, b);
        }

        private interface INumericStrategy
        {
            int Calculate(int a, int b);
        }

        private class AddStrategy : INumericStrategy
        {
            public int Calculate(int a, int b)
            {
                return a + b;
            }
        }

        private class SubtractStrategy : INumericStrategy
        {

            public int Calculate(int a, int b)
            {
                return a - b;
            }
        }

        private class MultiplyStrategy : INumericStrategy
        {
            public int Calculate(int a, int b)
            {
                return a*b;
            }
        }

        private class DivideStrategy : INumericStrategy
        {

            public int Calculate(int a, int b)
            {
                return a/b;
            }
        }

    }
}