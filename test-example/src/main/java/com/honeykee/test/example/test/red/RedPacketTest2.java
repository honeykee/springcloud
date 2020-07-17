package com.honeykee.test.example.test.red;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/7/16 3:55 下午
 * @since JDK 1.8
 */
public class RedPacketTest2 {

        public static void main(String[] args) {
//            getRed(3, 3);
//            getRed(3, 3, 1);
//            cut(3, 3 );

            getRed(30, 3);
            getRed(3, 3, 1);
            cut(30, 3 );
        }

        //二倍均值法
        public static void getRed(int totalAmount, int totalPeopleNum) {
            List<Integer> amountList = new ArrayList<Integer>();
            Integer restAmount = totalAmount;
            Integer restPeopleNum = totalPeopleNum;
            Random random = new Random();
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int min = 1;
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                amount = amount <= min ? min : amount;
                restAmount -= amount;
                restPeopleNum--;
                amountList.add(amount);
            }
            amountList.add(restAmount);

            for (int count = 1; count <= amountList.size(); count++) {
                System.out.println("第" + count + "个人拿到" + new BigDecimal(amountList.get(count - 1)).divide(new BigDecimal(100)));
            }
            System.out.println("1、本轮发红包中第" + (amountList.indexOf(Collections.max(amountList)) + 1) + "个人手气最佳");
        }

        //最大最小值法
        public static void getRed(int totalPeopleNum, float totalAmount, double min) {
            float money;
            double max;
            int i = 1;
            List math = new ArrayList();
            DecimalFormat df = new DecimalFormat("###.##");
            while (i < totalPeopleNum) {
                //保证即使一个红包是最大的了,后面剩下的红包,每个红包也不会小于最小值
                max = totalAmount - min * (totalPeopleNum - i);
                int k = (int) (totalPeopleNum - i) / 2;
                //保证最后两个人拿的红包不超出剩余红包
                if (totalPeopleNum - i <= 2) {
                    k = totalPeopleNum - i;
                }
                //最大的红包限定的平均线上下
                max = max / k;
                //保证每个红包大于最小值,又不会大于最大值
                money = (int) (min * 100 + Math.random() * (max * 100 - min * 100 + 1));
                money = (float) money / 100;
                //保留两位小数
                money = Float.parseFloat(df.format(money));
                totalAmount = (int) (totalAmount * 100 - money * 100);
                totalAmount = totalAmount / 100;
                math.add(money);
                System.out.println("第" + i + "个人拿到" + money);
                i++;
                //最后一个人拿走剩下的红包
                if (i == totalPeopleNum) {
                    math.add(totalAmount);
                    System.out.println("第" + i + "个人拿到" + totalAmount);
                }
            }
            //取数组中最大的一个值的索引
            System.out.println("2、本轮发红包中第" + (math.indexOf(Collections.max(math)) + 1) + "个人手气最佳");
        }

        //  线段切割法
        public static void cut(int totalAmount, int totalPeopleNum) {
            //验证参数合理校验
            //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
            //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
            int fen = totalAmount;
            if (fen < totalPeopleNum || totalPeopleNum < 1) {
                System.out.println("红包个数必须大于0，并且最小红包不少于1分");
            }
            List<Integer> boards = new ArrayList<>();
            boards.add(0);
            boards.add(fen);
            //红包个数和板砖个数的关系
            while (boards.size() <= totalPeopleNum) {
                int index = new Random().nextInt(fen - 1) + 1;
                if (boards.contains(index)) {
                    //保证板子的位置不相同
                    continue;
                }
                boards.add(index);
            }
            //计算每个红包的金额，将两个板子之间的钱加起来
            Collections.sort(boards);
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < boards.size() - 1; i++) {
                Integer e = boards.get(i + 1) - boards.get(i);
                list.add(e);
            }
            int i = 1;
            for (int count = 1; count <= list.size(); count++) {
                System.out.println("第" + count + "个人拿到" + new BigDecimal(list.get(count - 1)).divide(new BigDecimal(100)));
            }
            System.out.println("3、本轮发红包中第" + (list.indexOf(Collections.max(list)) + 1) + "个人手气最佳");
        }
}
