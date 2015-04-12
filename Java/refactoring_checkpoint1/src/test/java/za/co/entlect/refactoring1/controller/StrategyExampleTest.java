package za.co.entlect.refactoring1.controller;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StrategyExampleTest {

    private StrategyExample strategyExample = new StrategyExample();

    @Test
    public void testCalculates(){
        assertEquals(12, strategyExample.calculate(StrategyExample.ADD, 10, 2));
        assertEquals(8, strategyExample.calculate(StrategyExample.SUBTRACT, 10, 2));
        assertEquals(20, strategyExample.calculate(StrategyExample.MULTIPLY, 10, 2));
        assertEquals(5, strategyExample.calculate(StrategyExample.DIVIDE, 10, 2));
    }
}