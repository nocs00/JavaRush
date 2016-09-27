package com.javarush.test.level16.lesson05.task03;

/* Продвижение на политических дебатах
1. Разберитесь, что делает программа.
2. Нужно сделать так, чтобы Иванов сказал больше всего речей на политических дебатах.
3. Подумай, какой метод можно вызвать у объекта ivanov, чтобы Иванов разговаривал, пока не завершится всё свободное время.
*/

import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static int totalCountSpeeches = 200000;
    public static int soundsInOneSpeech = 1;

    public static void main(String[] args) throws InterruptedException {
        Politic ivanov = new Politic("Иванов"); //ivanov.join();
        Politic petrov = new Politic("Петров");
        Politic sidorov = new Politic("Сидоров");

//        while (Politic.totalSounds < totalCountSpeeches*soundsInOneSpeech) {
//            System.out.println("in main: total sounds -> "+Politic.totalSounds);
//            System.out.println("in main: "+ivanov.getName()+" -> "+ivanov.getCountSpeaches());
//            System.out.println("in main: "+petrov.getName()+" -> "+petrov.getCountSpeaches());
//            System.out.println("in main: "+sidorov.getName()+" -> "+sidorov.getCountSpeaches());
//        }
        while (!(ivanov.finish | petrov.finish | sidorov.finish)) {}
        System.out.println(ivanov);
        System.out.println(petrov);
        System.out.println(sidorov);
    }

    public static class Politic extends Thread {
        public volatile static Integer totalSounds = new Integer(0);
        private volatile int countSounds;
        public volatile boolean finish = false;

        public Politic(String name) {
            super(name);
            start();
        }

        public void run() {
            while (true) {
                yield();
                synchronized (totalSounds) {
                    if(totalSounds >= totalCountSpeeches * soundsInOneSpeech) {
                        break;
                    }

                    countSounds++;
                    totalSounds++;
                }
                try {
                    sleep(0);
                } catch (InterruptedException e) {}
            }
            finish = true;
        }

        @Override
        public String toString()
        {
            return getName()+" : "+countSounds+" : "+totalSounds;
        }
    }
}
