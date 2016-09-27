package com.javarush.test.level15.lesson12.home09;

//import org.junit.Test;

import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;

/**
 * Created by pdudenkov on 11.11.15.
 */
public class TestURLparser {
    //@Test
    public void testGetParams() throws Exception {
        ArrayList<String> params = new ArrayList<String>();
        params.add("lvl");
        params.add("view");
        params.add("name");
        params.add("obj");
        params.add("name");
        params.add("obj");
        params.add("oobj");

        String testURL = "javarush.ru/alpha/index.html?lvl=15&??view&&&name=Aobjmigo&obj=3.14&name=&obj=djsdcd&&?oobj=3.0";
        ArrayList<String> paramsTest = Solution.getParams(testURL);
        //assertEquals(params.size(), paramsTest.size());
        if (params.size() == paramsTest.size()) {
            for (int i = 0; i < params.size(); i++) {
                //assertEquals(params.get(i), paramsTest.get(i).split("=")[0]);
            }
        }
    }

}
