package com.javarush.test.level34.lesson02.home01.good_solution;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {


    public void recursion(final String expression, int countOperation) {
        //implement
        Locale.setDefault(Locale.ENGLISH);
        String s=expression;
        String s1=expression;
        String fulls1=expression;
        Integer count=countOperation;

        if(count==0)
            count+=(s.split("\\^").length - 1)+(s.split("\\/").length-1)+(s.split("\\*").length-1)
                    +(s.split("\\+").length-1)+(s.split("\\-").length-1)+(s.split("cos").length-1)+(s.split("sin").length-1)+(s.split("tan").length-1);

        int n1=expression.lastIndexOf("(");
        if(n1>=0)
        {
            String sp = expression.substring(n1);
            fulls1=sp.substring(0, sp.indexOf(")")+1);
            s1=sp.substring(1,sp.indexOf(")"));
        }

        int indS1=s.indexOf(fulls1);
        String s2=s1.trim();
        s2 = s2.replaceAll("-", "+-");

        s2=calculate(s2, "^");
        s2=calculate(s2, "/");
        s2=calculate(s2, "*");
        s2 = calculate(s2, "+");

        s2= new DecimalFormat("#.##").format(Double.parseDouble(s2));
        s2=s2.replaceAll(",",".");
        if(s2.endsWith(".0"))
            s2=String.valueOf(Math.round(Double.parseDouble(s2)));
        s=s.replace(fulls1, s2);


        int indS2=s.indexOf(s2);
        String sub=s.substring(0, indS2);
        String sub2=sub.trim();
        if(sub2.length()>3)
            sub2=sub2.substring(sub2.length()-3,sub2.length());
        String r=null;
        switch(sub2)
        {
            case "sin":
                r=new DecimalFormat("#.##").format(Math.sin(Math.toRadians(Double.parseDouble(s2))));
                r=r.replaceAll(",",".");
                break;
            case "cos":
                r=new DecimalFormat("#.##").format((Math.cos(Math.toRadians(Double.parseDouble(s2)))));
                r=r.replaceAll(",", ".");
                break;
            case "tan":
                r=new DecimalFormat("#.##").format((Math.tan(Math.toRadians(Double.parseDouble(s2)))));
                r=r.replaceAll(",", ".");
                break;
        }
        String z=null;
        if(r!=null)
        {
            z=s.substring(sub.lastIndexOf(sub2),indS2+s2.length());
            s=s.replace(z,r);
        }

        if(s.contains("^"))
        {
            if(z!=null)
            {
                s2=r;
            }
            indS2=s.indexOf(s2);
            sub=s.substring(indS2 + s2.length(), s.length());

            sub2=sub.trim();
            if(sub2.startsWith("^")&&(expression.indexOf("^")>indS1)) {
                String[] ms = sub2.split("\\^");
                StringTokenizer stt2 = new StringTokenizer(ms[1], "+/*^()");
                String st2 = null;
                st2 = stt2.nextToken();
                double d1 = 0;
                try {
                    d1 = Double.parseDouble(s2);
                } catch (NumberFormatException e1) {
                    d1 = 0;
                }
                double d2 = 0;
                try {
                    d2 = Double.parseDouble(st2);
                } catch (NumberFormatException e1) {
                    d2 = 0;
                }
                String res = new DecimalFormat("#.##").format(Math.pow(d1, d2));
                res=res.replaceAll(",",".");
                s=s.replace(s2 + sub.substring(0, sub.indexOf(st2) + st2.length()), res);
            }
        }

        //System.out.println();
        //System.out.println(s);


        s=s.replace("--", "+");
        if(s.contains("^")||s.contains("/")||s.contains("*")||s.contains("+")||((s.lastIndexOf("-")>0)&&(!s.contains("E-")))
                ||(s.contains("E-")&&(s.split("-").length!=2)))
        {
            s=s.replace("+-","-");
            recursion(s,count);
        }
        else
        {
            s=new DecimalFormat("#.##").format(Double.parseDouble(s));
            s=s.replaceAll(",",".");
            if(s.endsWith(".0"))
                s=String.valueOf(Math.round(Double.parseDouble(s)));
            System.out.println(s + " " + count);
        }
    }


    private String calculate(String s2,String op)
    {
        Locale.setDefault(Locale.ENGLISH);
        int i=s2.indexOf(op);
        if(op.equals("+"))
        {
            if(i==0) {
                s2=s2.substring(1);
                i = s2.indexOf(op);
            }
        }
        while(i>0)
        {
            String[] ms=s2.split(op.replace(op,"\\"+op));
            StringTokenizer stt1=new StringTokenizer(ms[0],"+/*^");
            String st1=null;
            while(stt1.hasMoreTokens())
                st1=stt1.nextToken();

            StringTokenizer stt2=new StringTokenizer(ms[1],"+/*^");
            String st2=null;
            st2=stt2.nextToken();

            double d1=0;
            try {
                d1 = Double.parseDouble(st1);

            }
            catch(NumberFormatException e1)
            {
                d1=0;
            }
            double d2=0;
            try {
                d2 = Double.parseDouble(st2);
            }
            catch(NumberFormatException e1)
            {
                d2 = 0;
            }

            String res=null;
            switch(op) {
                case "^":
                    if(d1<0) {
                        d1 = -d1;
                        st1=st1.substring(1);
                    }
                    res = new DecimalFormat("#.##").format(Math.pow(d1, d2));
                    res=res.replaceAll(",",".");
                    break;
                case "/":
                    res =  new DecimalFormat("#.##").format(d1 / d2);
                    res=res.replaceAll(",", ".");
                    break;
                case "*":
                    res =  new DecimalFormat("#.##").format(d1 * d2);
                    res=res.replaceAll(",", ".");
                    break;
                case "+":
                    res=  new DecimalFormat("#.##").format(d1 + d2);
                    res=res.replaceAll(",",".");
                    break;
            }

            String zs=st1 + op + st2;
            if(!s2.contains(zs))
            {
                st1=st1.replace("-", "+-");
                st2=st2.replace("-","+-");
            }
            s2=s2.replace(st1 + op + st2,res);
            s2=s2.replace("-+","-");
            i=s2.indexOf(op);
        }

        return s2;
    }


    public Solution() {
        //don't delete
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = null;

        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);

        s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recursion(s, 0);

        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recursion(s, 0);

        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recursion(s, 0);

        s = "cos(-30)";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recursion(s, 0);

        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recursion(s, 0);

        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);

        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.5 6 actually ");
        solution.recursion(s, 0);

        s = "0.005 ";
        System.out.print(s + " expected output 0.01 0 actually ");
        solution.recursion(s, 0);

        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recursion(s, 0);

        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);

        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recursion(s, 0);

        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);

        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);

        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);

        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);

        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);

        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);

        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);

        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);

        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);

        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);

        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);

        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);

        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);

        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);

        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);

        s = "-(-22+22*2)";
        System.out.print(s + "expected output -22 4 actually ");
        solution.recursion(s,0);

        s = "-2^(-2)";
        System.out.print(s + "expected output -0.25 3 actually ");
        solution.recursion(s,0);
    }

}
