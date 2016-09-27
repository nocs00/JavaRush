package com.javarush.test.level22.lesson05.home01;

public class ThisUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else
            if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
                System.out.println(getFormattedStringForSecondThread(t, e, string));
            } else {
                System.out.println(getFormattedStringForOtherThread(t, e, string));
            }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        String threadName = t.getName();
        String causedBy = e.getCause().toString();
        String customException = e.getClass().getSimpleName();

        String result = String.format(string, customException, causedBy, threadName);
        return result;
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        String threadName = t.getName();
        String causedBy = e.getCause().toString();
        String customException = e.getClass().getSimpleName();

        String result = String.format(string, causedBy, customException, threadName);
        return result;
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        String threadName = t.getName();
        String causedBy = e.getCause().toString();
        String customException = e.getClass().getSimpleName();

        String result = String.format(string, threadName, customException, causedBy);
        return result;
    }
}

