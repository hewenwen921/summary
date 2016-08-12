package com.wen.test.lottery;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽奖类
 * 每次都会抽中
 */
public class BigWheelDrawUtil {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            WechatLotteryDomain w = generateAward();
            if (null != w) {
                System.out.print(w.getId() + "------,");
            }
        }
    }

    /**
     * 给转盘的每个角度赋初始值
     *
     * @return
     */
    private final static List<WechatLotteryDomain> initDrawList = new ArrayList<WechatLotteryDomain>() {{
        add(new WechatLotteryDomain(1, "200", 10));
        add(new WechatLotteryDomain(2, "100", 10));
        add(new WechatLotteryDomain(3, "50", 30));
        add(new WechatLotteryDomain(4, "30", 25));
        add(new WechatLotteryDomain(5, "20", 20));
        add(new WechatLotteryDomain(6, "10", 0));
    }};

    /**
     * 生成奖项
     *
     * @return
     */
    public static WechatLotteryDomain generateAward() {
        List<WechatLotteryDomain> initData = initDrawList;
        //取两个数之间的随机数
        long result = randomnum(1, 100);
        int line = 0;
        int temp = 0;
        WechatLotteryDomain returnobj = null;
        for (int i = 0; i < initData.size(); i++) {
            WechatLotteryDomain obj2 = initData.get(i);
            int c = obj2.getV();
            temp = temp + c;
            //此处根据上面的，最大数
            line = 100 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }

    /**
     * 获取2个值之间的随机数
     */
    private static long randomnum(int smin, int smax) {
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }
}

class WechatLotteryDomain {

    private Integer id;

    /**
     * 中奖金额
     */

    private String prize;
    /**
     * 中奖率
     */
    private Integer v;

    public WechatLotteryDomain(Integer id, String prize, Integer v) {
        this.id = id;
        this.prize = prize;
        this.v = v;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}

