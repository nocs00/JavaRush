package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static void main(String[] args)
    {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "Ivanov");
        params.put("country", "Ukraine");
        params.put("city", "Kiev");

        Map<String, String> params2 = new LinkedHashMap<>();
        params2.put(null, "Tata");
        params2.put("Tata", null);
        params2.put("country", "Ukraine");

        Map<String, String> params3 = new LinkedHashMap<>();
        params3.put(null, null);
        params3.put(null, "Tata");
        params3.put("Tata", null);
        params3.put("country", "Ukraine");

        Map<String, String> params4 = new LinkedHashMap<>();
        params4.put("country", "Ukraine");
        params4.put(null, null);
        params4.put(null, "Tata");
        params4.put("Tata", null);

        Map<String, String> params5 = new LinkedHashMap<>();
        params5.put(null, null);
        params5.put(null, "Tata");
        params5.put("Tata", null);


        Map<String, String> params6 = new LinkedHashMap<>();
        params6.put(null, null);
        params6.put(null, null);
        params6.put(null, null);

        Map<String, String> params7 = new LinkedHashMap<>();

        StringBuilder sb = getCondition(params);
        StringBuilder sb2 = getCondition(params2);
        StringBuilder sb3 = getCondition(params3);
        StringBuilder sb4 = getCondition(params4);
        StringBuilder sb5 = getCondition(params5);
        StringBuilder sb6 = getCondition(params6);
        StringBuilder sb7 = getCondition(params7);

        int k = 5;
        k = k +5;
    }

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) continue;
            if (!first)
                result.append(" and ");
            result.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
            first = false;
        }
        return result;
    }
}
