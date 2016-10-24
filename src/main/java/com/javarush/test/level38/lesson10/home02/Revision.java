package com.javarush.test.level38.lesson10.home02;

public @interface Revision {
    //напиши свой код
    int revision();

    String comment() default "";
    Date date();
    Author[] authors() default {};
}
