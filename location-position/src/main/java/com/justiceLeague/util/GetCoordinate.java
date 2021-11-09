package com.justiceLeague.util;

import com.justiceLeague.model.Coordinate;
import com.justiceLeague.model.Round;
import com.justiceLeague.model.extem.APInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 三角定位实现类
 */
public class GetCoordinate {

    APInfo apInfo = new APInfo();

    static double totalWeight = 0;

    /**
     * 求三角形质心算法
     *
     * @param r1 Round对象
     * @param r2
     * @param r3
     * @return Coordinate对象
     */
    public static Round triCentroid(Round r1, Round r2, Round r3) {

        //算r1与r2的关系
        List<Round> r1r2 = getPoint(r1, r2);
        Round p1 = r1r2.get(0);
        //算r1r3的关系
        Round p2 = getPoint(r1, r3).get(0);
        //算r2r3的关系
        Round p3 = getPoint(r2, r3).get(0);

        double x =(p1.getX()+p2.getX()+p3.getX())/3;
        double y =(p1.getY()+p2.getY()+p3.getY())/3;

        return new Round(x,y,0);
    }


    /**
     * 判断两圆的关系 获取交点
     */

    public static List<Round> getPoint(Round one, Round two) {
        double x1 = one.getX();
        double y1 = one.getY();
        double r1 = one.getR();

        double x2 = two.getX();
        double y2 = two.getY();
        double r2 = two.getR();

        List<Round> rounds = new ArrayList<>();
        //判断两点直线距离
        double s = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        //两圆相交
        if (s < (r1 + r2)) {

            if (y1 == y2 && x1 != x2) {
                double a = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2)) / (2 * x2 - 2 * x1);
                double t = r1 * r1 - (a - x1) * (a - x1);
                rounds.add(new Round(a, y1 + Math.sqrt(t), 0));
                rounds.add(new Round(a, y1 - Math.sqrt(t), 0));
            } else if (y1 != y2) {
                double k, disc, a, b, c;
                k = (2 * x1 - 2 * x2) / (2 * y2 - 2 * y1);
                disc = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2) - (y1 * y1 - y2 * y2)) / (2 * y2 - 2 * y1);// 直线偏移量
                a = (k * k + 1);
                b = (2 * (disc - y1) * k - 2 * x1);
                c = (disc - y1) * (disc - y1) - r1 * r1 + x1 * x1;
                rounds.add(new Round(((-b) + Math.sqrt(disc)) / (2 * a), ((-b) + Math.sqrt(disc)) / (2 * a), 0));
                rounds.add(new Round(((-b) - Math.sqrt(disc)) / (2 * a), k * (((-b) + Math.sqrt(disc)) / (2 * a)) + disc, 0));
            }

            //同心圆
        } else if (x1 == x2 && y1 == y2) {
            rounds.add(one);
            //相离
        } else if (s > r1 + r2) {

            rounds.add(one);
            //内含
        } else if (s < Math.abs(r1 - r2)) {

            rounds.add(one);
            //相切
        } else if (s == Math.abs(r1 - r2) || s == r1 + r2) {
            if (y1 == y2 && x1 != x2) {
                double x = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2)) / (2 * x2 - 2 * x1);
                double y = y1;
                rounds.add(new Round(x, y, 0));
            } else if (y1 != y2) {
                double k, disp, a, b;
                k = (2 * x1 - 2 * x2) / (2 * y2 - 2 * y1);
                disp = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2) - (y1 * y1 - y2 * y2)) / (2 * y2 - 2 * y1);// 直线偏移量
                a = (k * k + 1);
                b = (2 * (disp - y1) * k - 2 * x1);
                double x = (-b) / (2 * a);
                double y = k * x + disp;
                rounds.add(new Round(x, y, 0));
            }

        }

        return rounds;

    }


    /**
     * 根据信号强度获取距离
     *
     * @return
     */
    public static double getDistanceByRiss(double rssi) {


        Double Rssi = Math.abs(rssi);
        double power = (Rssi - 60) / (10.0 * 3.3);
        //93=10米    60=1米
        String location = String.valueOf(Math.pow(10, power));
        return Double.parseDouble(location.substring(0, 6));
    }


}
