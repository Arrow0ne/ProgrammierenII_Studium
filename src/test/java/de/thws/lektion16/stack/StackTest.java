package de.thws.lektion16.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {
    private Stack neu;
    private Object test;

    @BeforeEach
    void reset() {
        neu = new Stack();
        test = new Object();
    }

    @Test
    public void test_stack_validPush_success() {
        assertTrue(neu.push(test));
    }

    @Test
    public void test_stack_validPop_success() {
        neu.push("Hallo");
        String test = "test";
        neu.push(test);

        assertEquals(test, neu.pop());
    }

    @Test
    void test_pop_onEmptyStack() {
        try {
            neu.pop();
            fail("Es hätte eine RuntimeException geworfen werden müssen");
        } catch (RuntimeException e) {

        }
    }


}