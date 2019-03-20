package com.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoodsList {
    public static void main(String[] args) {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods("书包",12));
        list.add(new Goods("铅笔",11));
        list.add(new Goods("橡皮",13));
        list.add(new Goods("小刀",14));
        Collections.sort(list);
        for (Goods goods : list){
            System.out.println("商品名: " + goods.getName() + ",价格：" + goods.getPrice());
        }
    }
}
