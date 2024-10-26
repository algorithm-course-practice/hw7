package org.example;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        HashSet<Integer> destroyedRooms = new HashSet<>();
        List<Integer> results = new ArrayList<>();
        int countDestroyed = 0;
        for (int i = 0; i < actionList.size() && i < maxDoors; i++) {
            boolean opType = actionList.get(i).isLook;
            int roomNumber = actionList.get(i).doorNumber;

            if (!opType) {
                destroyedRooms.add(roomNumber);
            } else {

                for (int destroyedRoom : destroyedRooms) {
                    if (destroyedRoom <= roomNumber) {
                        countDestroyed++;
                        if (i + 1 == actionList.size()) {
                            countDestroyed++;
                        }
                    }
                }
                int realRoomNumber = roomNumber + countDestroyed;
                if (countDestroyed > 0) {
                    countDestroyed = 0;
                }
                results.add(realRoomNumber);
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
        List<Integer> soldiers = new ArrayList<>();
        for (int i = 1; i <= maxUnits; i++) {
            soldiers.add(i);
        }

        List<Integer> eliminationOrder = new ArrayList<>();
        int index = 0;

        while (!soldiers.isEmpty()) {
            index = (index + leaveInterval - 1) % soldiers.size();
            eliminationOrder.add(soldiers.remove(index));
        }

        return eliminationOrder;
    }

}
