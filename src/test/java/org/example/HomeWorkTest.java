package org.example;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void checkFirst() {
        TestCase1 testCase = generateTestCase1();
        TestCase1 testCase2 = generateTestCase2();
        TestCase1 testCase3 = generateTestCase3();

        assertEquals(testCase.expected, homeWork.getOriginalDoorNumbers(testCase.maxDoors, testCase.actionList));
        assertEquals(testCase2.expected, homeWork.getOriginalDoorNumbers(testCase2.maxDoors, testCase2.actionList));
        assertEquals(testCase3.expected, homeWork.getOriginalDoorNumbers(testCase3.maxDoors, testCase3.actionList));
    }

    @Test
    void checkSecond(){
        assertIterableEquals(asList("3 1 5 2 4".split(" ")), homeWork.getLeaveOrder(5, 3));
    }


    private TestCase1 generateTestCase1() {
        TestCase1 testCase = new TestCase1();
        testCase.parseExpected("5\n" +
                "4\n" +
                "6\n" +
                "4\n" +
                "7");
        testCase.parseInput("20 7\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5");
        return testCase;
    }

    private TestCase1 generateTestCase2() {
        TestCase1 testCase = new TestCase1();
        testCase.parseExpected(
                "2\n" +
                        "4\n");
        testCase.parseInput("4 4\n" +
                "D 1\n" +
                "D 2\n" +
                "L 1\n" +
                "L 2\n");
        return testCase;
    }
    /*
    1 2 3 4
    2 3 4
    2 4
     */

    private TestCase1 generateTestCase3() {
        TestCase1 testCase = new TestCase1();
        testCase.parseExpected(
                "3\n" +
                        "4\n");
        testCase.parseInput("4 4\n" +
                "D 2\n" +
                "D 1\n" +
                "L 1\n" +
                "L 2\n");
        return testCase;
    }

    @RequiredArgsConstructor
    static class TestCase1 {
        int maxDoors;
        List<Action> actionList = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        public void parseInput(String input) {
            String[] lines = input.split("(\n|\r|\r\n)");
            maxDoors = Integer.parseInt(lines[0].split(" ")[0]);
            Arrays.stream(lines)
                    .skip(1)
                    .map(Action::parse)
                    .forEach(actionList::add);

        }


        public void parseExpected(String output) {
            String[] lines = output.split("(\n|\r|\r\n)");
            Arrays.stream(lines)
                    .map(Integer::parseInt)
                    .forEach(expected::add);
        }
    }

    @RepeatedTest(10)
    public void TreapTest() {
        Treap<Integer> treap = new Treap<>();

        for (int i = 0; i < 100; i++) {
            treap.add(i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals(i, treap.findNodeByIndex(i).key);
        }

    }

    @Test
    public void TreapDeleteNodeTest() {
        Treap<Integer> treap = new Treap<>();
        for (int i = 0; i < 10; i++) {
            treap.add(i);
        }

        for (int i = 0; i < 10; i++) {

            treap.removeNode(treap.findNodeByIndex(0));

        }

        assertNull(treap.root);

    }

}