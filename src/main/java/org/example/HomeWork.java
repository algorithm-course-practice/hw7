package org.example;


import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        Treap<Integer> treap = new Treap<>();
        for (int i = 1; i < maxDoors + 1; i++) {
            treap.add(i);
        }

        List<Integer> result = new ArrayList<>();

        for (Action action : actionList) {
            Treap.Node<Integer> nodeByIndex = treap.findNodeByIndex(action.doorNumber - 1);
            if (action.isLook) result.add(nodeByIndex.key);
            else treap.removeNode(nodeByIndex);
        }
        return result;
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
    public List<String> getLeaveOrder(int maxUnits, int leaveInterval) {
        /* 1) size = 5; pos = 0; ind to remove = 2
           2) size = 4; pos = 2; ind to remove = 0
           3) size = 3; pos = 0; ind to remove = 2
           4) size = 2; pos = 2; ind to remove = 0
           5) size = 1; pos = 1; ind to remove = 0
                    */
        Treap<Integer> integerTreap = new Treap<>();
        List<String> result = new ArrayList<>();
        for (int i = 1; i < maxUnits + 1; i++) {
            integerTreap.add(i);
        }

        int pos = 0;
        for (int i = 0; i < maxUnits - 1; i++) {
            pos = leaveInterval - pos - 1;
            Treap.Node<Integer> nodeByIndex = integerTreap.findNodeByIndex(pos);
            result.add(String.valueOf(nodeByIndex.key));
            integerTreap.removeNode(nodeByIndex);
        }
        result.add(String.valueOf(integerTreap.root.key));
        return result;
    }

}
