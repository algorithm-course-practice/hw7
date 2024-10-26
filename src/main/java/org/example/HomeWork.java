package org.example;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        Set<Integer> rooms = new HashSet<>();
        List<Integer> results = new ArrayList<>();
        int count;

        for (int i = 0; i < Math.min(actionList.size(), maxDoors); i++) {
            Action action = actionList.get(i);
            int roomNumber = action.doorNumber;

            if (!action.isLook) {
                rooms.add(roomNumber);
            } else {
                count = 0;
                for (int room : rooms) {
                    if (room <= roomNumber) {
                        count++;
                    }
                }

                if (i == actionList.size() - 1) {
                    count++;
                }

                int trueRoomNum = roomNumber + count;
                results.add(trueRoomNum);
            }
        }

        return results;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1521">https://acm.timus.ru/problem.aspx?space=1&num=1521</a><br/>
     * <h2>Пошагово</h2>
     * Для 5 3 входных данных:<br/>
     * _ -> 3 позиции<br/>
     * _ 1 2 <b>3</b> 4 5 => 3 <br/>
     * <b>1</b> 2 _ 4 5 => 1 <br/>
     * _ 2 4 <b>5</b> => 5 <br/>
     * <b>2</b> 4 _ => 2 <br/>
     * _ <b>4</b> => 4
     */
    public List<Integer> getLeaveOrder(int maxUnits, int leaveInterval) {
        List<Integer> units = initializeUnits(maxUnits);
        List<Integer> leaveOrder = new ArrayList<>();
        int index = 0;

        while (!units.isEmpty()) {
            index = getNextIndex(index, leaveInterval, units.size());
            leaveOrder.add(units.remove(index));
        }

        return leaveOrder;
    }

    private List<Integer> initializeUnits(int maxUnits) {
        List<Integer> units = new ArrayList<>();
        for (int i = 1; i <= maxUnits; i++) {
            units.add(i);
        }
        return units;
    }

    private int getNextIndex(int currentIndex, int interval, int size) {
        return (currentIndex + interval - 1) % size;
    }
}
