package org.example;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.example.Action.destroy;
import static org.example.Action.look;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    public void orderTest(){
        HomeWork homeWork = new HomeWork();
        List<Integer> res = homeWork.getLeaveOrder(5, 3);
        String result = getResult(res);
        assertEquals("3 1 5 2 4", result);
        res = homeWork.getLeaveOrder(5, 1);
        result = getResult(res);
        assertEquals("1 2 3 4 5", result);
        res = homeWork.getLeaveOrder(1, 1);
        result = getResult(res);
        assertEquals("1", result);
        res = homeWork.getLeaveOrder(10, 3);
        result = getResult(res);
        assertEquals("3 6 9 2 7 1 8 5 10 4", result);
        res = homeWork.getLeaveOrder(10, 10);
        result = getResult(res);
        assertEquals("10 1 3 6 2 9 5 7 4 8", result);
        assertThrows(IllegalArgumentException.class, () -> homeWork.getLeaveOrder(1, 2));
        assertThrows(IllegalArgumentException.class, () -> homeWork.getLeaveOrder(0, 2));
        assertThrows(IllegalArgumentException.class, () -> homeWork.getLeaveOrder(1, -2));
    }

    private static String getResult(List<Integer> res) {
        List<String> strRes = res.stream().map(String::valueOf).collect(Collectors.toList());
        String result = String.join(" ", strRes);
        return result;
    }


    @Test
    void checkFirst() {
        TestCase1 testCase = generateTestCase1();

        assertEquals(testCase.expected, homeWork.getOriginalDoorNumbers(testCase.maxDoors, testCase.actionList));
    }

    @Test
    void checkSecond(){
        assertEquals(asList("3 1 5 2 4".split(" ")), homeWork.getLeaveOrder(5, 3));
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