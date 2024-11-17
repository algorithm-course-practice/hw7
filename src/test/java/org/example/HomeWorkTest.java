package org.example;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;


class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void checkFirst() {
        TestCase1 testCase = generateTestCase1();

        int maxDoors = 20;
        List<Action> actions = new ArrayList<>();
        actions.add(Action.parse("L 5"));
        actions.add(Action.parse("D 5"));
        actions.add(Action.parse("L 4"));
        actions.add(Action.parse("L 5"));
        actions.add(Action.parse("D 5"));
        actions.add(Action.parse("L 4"));
        actions.add(Action.parse("L 5"));

        List<Integer> listActual = homeWork.getOriginalDoorNumbers(maxDoors, testCase.actionList);
        assertEquals(testCase.expected, listActual);
    }

    @Test
    void checkSecond(){
        List<Integer> listActual = homeWork.getLeaveOrder(5, 3);
        assertEquals(asList(3, 1, 5, 2, 4), listActual);
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


    @RequiredArgsConstructor
    static class TestCase1 {
        int maxDoors;
        List<Action> actionList = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        public void parseInput(String input) {
            String[] lines = input.split("(\n|\r|\r\n)");
            maxDoors = Integer.valueOf(lines[0].split(" ")[0]);
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

}