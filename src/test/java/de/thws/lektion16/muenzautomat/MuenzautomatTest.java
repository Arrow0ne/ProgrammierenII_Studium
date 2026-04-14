package de.thws.lektion16.muenzautomat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinimalChangeCalculatorTest
{
    private final ChangeCalculator calc = new NewChangeCalculator();

    @Test
    void testExactTwoEuro()
    {
        int[] result = calc.getChange(2, 0);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 1}, result);
    }

    @Test
    void testZero()
    {
        int[] result = calc.getChange(0, 0);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, result);
    }

    @Test
    void test387Cent()
    {
        // 3,87€ → 1×2€, 1×1€, 1×50ct, 1×20ct, 1×10ct, 1×5ct, 1×2ct, 0×1ct
        int[] result = calc.getChange(3, 87);
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1, 1, 1}, result);
    }

    @Test
    void testOnlyCents()
    {
        // 0,99€ → 0×2€, 0×1€, 1×50ct, 2×20ct, 0×10ct, 1×5ct, 2×2ct, 0×1ct
        int[] result = calc.getChange(0, 99);
        assertArrayEquals(new int[]{0, 2, 1, 0, 2, 1, 0, 0}, result);
    }

    @Test
    void testOnlyOneCent()
    {
        int[] result = calc.getChange(0, 1);
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0}, result);
    }

    @Test
    void testArrayLength()
    {
        int[] result = calc.getChange(1, 50);
        assertEquals(8, result.length);
    }

    @Test
    void testTotalValueIsCorrect()
    {
        // Summe der Münzwerte muss dem Eingabewert entsprechen
        int euros = 4, cent = 63;
        int[] result = calc.getChange(euros, cent);
        int[] coinValues = {1, 2, 5, 10, 20, 50, 100, 200};

        int sum = 0;
        for (int i = 0; i < 8; i++)
            sum += result[i] * coinValues[i];

        assertEquals(euros * 100 + cent, sum);
    }
}