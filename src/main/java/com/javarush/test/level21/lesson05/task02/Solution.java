package com.javarush.test.level21.lesson05.task02;

import java.util.HashSet;
import java.util.Set;

/* Исправить ошибку
Сравнение объектов Solution не работает должным образом. Найти ошибку и исправить.
Метод main не участвует в тестировании.
*/
public class Solution
{
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        Solution n = null;
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;
        else
            n = (Solution)o;

        if (first == null && last == null && n.first == null && n.last == null)
            return true;
        if (first == null && n.first != null)
            return false;
        if (n.first == null && first != null)
            return false;
        if (last == null && n.last != null)
            return false;
        if (n.last == null && last != null)
            return false;

        return first!=null?
                (last!=null?
                        (n.first!=null&&n.last!=null?
                                n.first.equals(first)&&n.last.equals(last):false
                        ):
                        n.first!=null&&n.last==null?n.first.equals(first):false
                ):
                (last!=null?
                        (n.first==null&&n.last!=null?
                                n.last.equals(last):false
                        ):
                        n.first==null&&n.last==null?true:false
                );
    }

    @Override
    public int hashCode()
    {
        return (this.first==null?0:(this.first.hashCode()*31)) + (this.last==null?0:this.last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution(null, null));
        System.out.println(s.contains(new Solution(null, null)));
    }


}
