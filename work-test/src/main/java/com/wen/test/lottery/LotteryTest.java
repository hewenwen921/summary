package com.wen.test.lottery;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * 抽奖，每次不一定能中
 */
public class LotteryTest {
    //1000000
    private static final int MULRIPLE = 0xf4240;

    public static void main(String[] args) {

        //全部的中奖概率
        List<LotteryDomain> orignalRates = new ArrayList<LotteryDomain>();
        orignalRates.add(new LotteryDomain(1, new BigDecimal(2)));
        orignalRates.add(new LotteryDomain(2, new BigDecimal(5)));
        orignalRates.add(new LotteryDomain(3, new BigDecimal(8)));
        orignalRates.add(new LotteryDomain(4, new BigDecimal(3)));

        //置换列表顺序
        Collections.shuffle(orignalRates);

        //抽奖后，可以通过id查询相的奖品
        int l = draw(orignalRates);
        System.out.println("中奖的概率：" + MULRIPLE);
    }

    private static int draw(List<LotteryDomain> orignalRates) {
        Long lastScope = 0L;
        Map<Integer, Long[]> itemScopes = new HashMap<Integer, Long[]>();
        //循环全部中奖概率
        for (LotteryDomain item : orignalRates) {
            int itemId = item.getId();
            // 划分区间，概率/100*1000000
            Long currentScope = lastScope + item.getProbability().divide(new BigDecimal(100)).multiply(new BigDecimal(MULRIPLE)).longValue();
            itemScopes.put(itemId, new Long[]{lastScope + 1, currentScope});
//            itemQuantity.put(itemId, item.getStock());
            System.out.println(lastScope + 1 + "-------------" + currentScope);
            lastScope = currentScope;
        }

        // 获取1-1000000之间的一个随机数
        Integer luckyNumber = new Random().nextInt(MULRIPLE);
        int itemId = 0;

        if ((null != itemScopes) && !itemScopes.isEmpty()) {
            Set<Map.Entry<Integer, Long[]>> entrySets = itemScopes.entrySet();
            for (Map.Entry<Integer, Long[]> m : entrySets) {
                int key = m.getKey();
                //判断当前随机数是否在两个数之间
                if (luckyNumber >= m.getValue()[0] && luckyNumber <= m.getValue()[1]) {
                    itemId = key;
                    break;
                }
            }
        }

        return itemId;
    }
}

/**
 * 概率信息
 * 里面关联奖品信息
 */
class LotteryDomain {

    //奖品编号
    int id;

    //概率
    BigDecimal probability;

    LotteryDomain(int id, BigDecimal probability) {
        this.id = id;
        this.probability = probability;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
