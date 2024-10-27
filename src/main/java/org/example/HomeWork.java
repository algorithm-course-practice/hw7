package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        return null;
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
        if (maxUnits < 1
                || maxUnits > 100000
        || leaveInterval < 1
        || leaveInterval > maxUnits){
            throw new IllegalArgumentException("Некорректные данные maxUnits и leaveInterval");
        }
        List<Integer> result = new ArrayList<>();
        List<Integer> orderList = new ArrayList<>();
        for (int i = 1; i < maxUnits + 1; i++){
            orderList.add(i);
        }
        int offset = 0;
        while (orderList.size() > 1){
            int numberToDelete = (offset + leaveInterval - 1) % orderList.size();
            // добавляем в удаляемого солдата в список
            result.add(orderList.get(numberToDelete));
            // удаляем найденного солдата из очереди на плацу
            orderList.remove(orderList.get(numberToDelete));
            // сохраняем индекс для продолжения удаления солдата
            offset = numberToDelete;
        }
        // добавляем последнего удаляемого солдата
        result.add(orderList.get(0));
        return result;
    }


}
