package com.fanxl.admin.utils;

import com.fanxl.admin.entity.StockIn;
import java.lang.reflect.Field;

/**
 * @author fanxl12
 * @description
 * @date 2019/11/4 14:40
 */
public class SetValueUtils {

    public static void setValue(Object obj) {
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null) {
                    if (field.getType() == String.class) {
                        field.set(obj, "");
                    }
                }
            }catch (IllegalAccessException e) {
                e.fillInStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StockIn stockIn = new StockIn();
        stockIn.setGoodsCode("H1234");
        SetValueUtils.setValue(stockIn);
        System.out.println(stockIn.getBrandName());
    }

}
