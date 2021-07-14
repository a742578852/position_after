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
     * @param  r1 Round对象
     * @param  r2
     * @param  r3
     * @return  Coordinate对象
     */
    public static Coordinate triCentroid(Round r1, Round r2, Round r3) {

        /*有效交叉点1*/
        Coordinate p1 = null;
        /*有效交叉点2*/
        Coordinate p2 = null;
        /*有效交叉点3*/
        Coordinate p3 = null;

        /*三点质心坐标*/
        Coordinate centroid = new Coordinate();

        /*r1,r2交点*/
        List<Coordinate> intersections1 = intersection(r1.getX(), r1.getY(), r1.getR(),
                r2.getX(), r2.getY(), r2.getR());

        if (intersections1 != null && !intersections1.isEmpty()) {
            for (Coordinate intersection :intersections1) {
                if (p1==null&&Math.pow(intersection.getX()-r3.getX(),2)
                        + Math.pow(intersection.getY()-r3.getY(),2) <= Math.pow(r3.getR(),2)) {
                    p1 = intersection;
                }else if(p1!=null){
                    if(Math.pow(intersection.getX()-r3.getX(),2) + Math.pow(intersection.getY()
                            -r3.getY(),2)<= Math.pow(r3.getR(),2)){
                        if(Math.sqrt(Math.pow(intersection.getX()-r3.getX(),2)
                                + Math.pow(intersection.getY()-r3.getY(),2))>Math.sqrt(Math.pow(p1.getX()
                                -r3.getX(),2) + Math.pow(p1.getY()-r3.getY(),2))){
                            p1 = intersection;
                        }
                    }
                }
            }
        } else {//没有交点定位错误
            return null;
        }

        /*r1,r3交点*/
        List<Coordinate> intersections2 = intersection(r1.getX(), r1.getY(), r1.getR(),
                r3.getX(), r3.getY(), r3.getR());

        if (intersections2 != null && !intersections2.isEmpty()) {
            for (Coordinate intersection : intersections2) {//有交点
                if (p2==null&&Math.pow(intersection.getX()-r2.getX(),2)
                        + Math.pow(intersection.getY()-r2.getY(),2) <= Math.pow(r2.getR(),2)) {
                    p2 = intersection;

                }else if(p2!=null){
                    if(Math.pow(intersection.getX()-r2.getX(),2) + Math.pow(intersection.getY()
                            -r2.getY(),2) <= Math.pow(r2.getR(),2)){
                        if(Math.pow(intersection.getX()-r2.getX(),2) + Math.pow(intersection.getY()
                                -r2.getY(),2)>Math.sqrt(Math.pow(p2.getX()-r2.getX(),2)
                                + Math.pow(p2.getY()-r2.getY(),2))){
                            p1 = intersection;
                        }
                    }
                }
            }
        } else {//没有交点定位错误
            return null;
        }

        /*r1,r2交点*/
        List<Coordinate> intersections3 = intersection(r2.getX(), r2.getY(), r2.getR(),
                r3.getX(), r3.getY(), r3.getR());

        if (intersections3 != null && !intersections3.isEmpty()) {
            for (Coordinate intersection : intersections3) {//有交点
                if (Math.pow(intersection.getX()-r1.getX(),2)
                        + Math.pow(intersection.getY()-r1.getY(),2) <= Math.pow(r1.getR(),2)) {
                    p3 = intersection;
                }else if(p3!=null){
                    if(Math.pow(intersection.getX()-r1.getX(),2) + Math.pow(intersection.getY()
                            -r1.getY(),2) <= Math.pow(r1.getR(),2)){
                        if(Math.pow(intersection.getX()-r1.getX(),2) + Math.pow(intersection.getY()
                                -r1.getY(),2)>Math.sqrt(Math.pow(p3.getX()-r1.getX(),2)
                                + Math.pow(p3.getY()-r1.getY(),2))){
                            p3 = intersection;
                        }
                    }
                }
            }
        } else {//没有交点定位错误
            return null;
        }

        /*质心*/
        centroid.setX((p1.getX()+p2.getX()+p3.getX())/3);
        centroid.setY((p1.getY()+p2.getY()+p3.getY())/3);

        return centroid;
    }


    public static List<Coordinate> intersection(double x1, double y1, double r1,
                                                double x2, double y2, double r2) {

        double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));// 两圆心距离

        if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < (r1 + r2)) {// 两圆相交

        }

        List<Coordinate> points  =new ArrayList<Coordinate>();//交点坐标列表

        Coordinate coor;

        if (d > r1 + r2 || d < Math.abs(r1 - r2)) {//相离或内含
            return null;
        } else if (x1 == x2 && y1 == y2) {//同心圆
            return null;
        } else if (y1 == y2 && x1 != x2) {
            double a = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2)) / (2 * x2 - 2 * x1);
            if (d == Math.abs(r1 - r2) || d == r1 + r2) {// 只有一个交点时
                coor=new Coordinate();
                coor.setX(a);
                coor.setY(y1);
                points.add(coor);
            } else{// 两个交点
                double t = r1 * r1 - (a - x1) * (a - x1);
                coor=new Coordinate();
                coor.setX(a);
                coor.setY(y1 + Math.sqrt(t));
                points.add(coor);
                coor = new Coordinate();
                coor.setX(a);
                coor.setY(y1 - Math.sqrt(t));
                points.add(coor);
            }
        } else if (y1 != y2) {
            double k, disp;
            k = (2 * x1 - 2 * x2) / (2 * y2 - 2 * y1);
            disp = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2) - (y1 * y1 - y2 * y2))
                    / (2 * y2 - 2 * y1);// 直线偏移量
            double a, b, c;
            a = (k * k + 1);
            b = (2 * (disp - y1) * k - 2 * x1);
            c = (disp - y1) * (disp - y1) - r1 * r1 + x1 * x1;
            double disc;
            disc = b * b - 4 * a * c;// 一元二次方程判别式
            if (d == Math.abs(r1 - r2) || d == r1 + r2) {
                coor=new Coordinate();
                coor.setX((-b) / (2 * a));
                coor.setY(k * coor.getX() + disp);
                points.add(coor);
            } else {
                coor=new Coordinate();
                coor.setX(((-b) + Math.sqrt(disc)) / (2 * a));
                coor.setY(k * coor.getX() + disp);
                points.add(coor);
                coor=new Coordinate();
                coor.setX(((-b) - Math.sqrt(disc)) / (2 * a));
                coor.setY(k * coor.getX() + disp);
                points.add(coor);
            }
        }
        return points;
    }

    /**
     * 根据信号强度获取距离
     * @return
     */
    public static double getDistanceByRiss(double rssi){


        Double Rssi = Math.abs(rssi);
        double power = (Rssi - 60) / (10.0 * 3.3);
        //93=10米    60=1米
        String location=String.valueOf(Math.pow(10, power));
        return Double.parseDouble(location.substring(0,6));
    }




}
