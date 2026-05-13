package com.gxf.demo;

import java.util.Scanner;

public class Rate {

    public static double rate(double a, double b, double c, int cnt, int ina) {
        double rate = 1, x, jd = 0.1, side = 0.1, i = 1;
        do {
            x = a / b - (Math.pow(1 + rate, c) - 1) / (Math.pow(rate + 1, c) * rate);
            if (x * side > 0) {
                side = -side;
                jd *= 10;
            }
            rate += side / jd;
        } while (i++ < cnt && Math.abs(x) >= 1 / Math.pow(10, ina));
//        if (i > cnt)
//            return Double.NaN;
        return rate;
    }


    public static void main(String agrs[]) {
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入借款本金：");
            double a = sc.nextDouble();
            System.out.print("请输入月还款额：");
            double b = sc.nextDouble();
            System.out.print("请输入还款月数：");
            int c = sc.nextInt();
            System.out.print("请输入计算次数(次数越高越准确，推荐大于200)：");
            int cnt = sc.nextInt();
            System.out.print("请输入精确位数：");
            int ina = sc.nextInt();
            // Double 现值 = 7944760.00d;
            // Double 年金 = 186627.21d;
            // Double 期数 = 48d;

            // 计算200次，比Excel20次要精确，误差精确到小数点后10位
            double rate = rate(a, b, c, cnt, ina)*100;
            double yearRate = rate * 12;
            System.out.println("月利率:"+rate+"%,年利率："+yearRate+"%");
        }
    }
}

