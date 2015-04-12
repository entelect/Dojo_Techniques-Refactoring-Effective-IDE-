package za.co.entlect.refactoring2.controller;

import java.util.HashMap;
import java.util.Map;

public class StrategyExample {

    public static final String ADD = "ADD";
    public static final String SUBTRACT = "SUBTRACT";
    public static final String MULTIPLY = "MULTIPLY";
    public static final String DIVIDE = "DIVIDE";

    private Map<String, NumericStrategy> numericStrategyMap = new HashMap<String, NumericStrategy>();

    public StrategyExample(){
        numericStrategyMap.put(ADD, new AddStrategy());
        numericStrategyMap.put(SUBTRACT, new SubtractStrategy());
        numericStrategyMap.put(MULTIPLY, new MultiplyStrategy());
        numericStrategyMap.put(DIVIDE, new DivideStrategy());
    }

    public int calculate(String operation, int a, int b){
        return numericStrategyMap.get(operation).calculate(a, b);
    }

    interface NumericStrategy {
        int calculate(int a, int b);
    }

    class AddStrategy implements NumericStrategy {

        @Override
        public int calculate(int a, int b) {
            return a+b;
        }
    }

    class SubtractStrategy implements NumericStrategy {

        @Override
        public int calculate(int a, int b) {
            return a-b;
        }
    }

    class MultiplyStrategy implements NumericStrategy {

        @Override
        public int calculate(int a, int b) {
            return a*b;
        }
    }

    class DivideStrategy implements NumericStrategy{

        @Override
        public int calculate(int a, int b) {
            return a/b;
        }
    }

}
