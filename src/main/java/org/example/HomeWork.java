package org.example;


import java.util.*;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {

        List<Integer> results = new ArrayList<>();

        Treap treap = new Treap();
        // заполняем расположение комнат
        for (int i = 1; i <= maxDoors; i++) {
            treap.insert(i);
        }

        treap.print();

        for (Action action : actionList) {
            int doorNumber = action.getDoorNumber();
            Node node = treap.search(doorNumber);

            if (action.isLook()) {
                //если найдена закрытая дверь
                if (node != null) {
                    results.add(node.getValue());
                } else {
                    //иначе идем к двери выше
                    results.add(treap.upperBound(doorNumber).getValue());
                }
            } else {
                //если найдена дверь, то открываем
                if (node != null) {
                    treap.delete(doorNumber);
                } else {
                    //иначе открываем дверь выше
                    treap.delete(treap.upperBound(doorNumber).getValue());
                }
            }

        }
        treap.print();

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
        List<Integer> leaveOrder = new ArrayList<>();
        List<Integer> rooms = new LinkedList<>();

        // Заполняем комнаты от 1 до maxUnits
        for (int i = 1; i <= maxUnits; i++) {
            rooms.add(i);
        }

        int currentIndex = 0;

        // Пока есть комнаты, удаляем их с заданным интервалом
        while (!rooms.isEmpty()) {
            currentIndex = (currentIndex + leaveInterval - 1) % rooms.size();
            leaveOrder.add(rooms.remove(currentIndex));
        }

        return leaveOrder;
    }

}
