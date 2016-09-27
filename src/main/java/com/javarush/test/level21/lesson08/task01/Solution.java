package com.javarush.test.level21.lesson08.task01;

import java.util.LinkedHashMap;
import java.util.Map;

/* Глубокое клонирование карты
Клонируйтие объект класса Solution используя глубокое клонирование.
Данные в карте users также должны клонироваться.
Метод main изменять нельзя.
*/
public class Solution implements Cloneable {
    @Override
    protected Solution clone() throws CloneNotSupportedException
    {
        Map<String, User> users_copy = new LinkedHashMap();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            users_copy.put(new String(entry.getKey()), entry.getValue().clone());
        }
        Solution s = new Solution();
        s.users = users_copy;
        return s;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User {
        int age;
        String name;

        @Override
        protected User clone() throws CloneNotSupportedException
        {
            User u = new User(age, new String(name));
            return u;
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
