package com.dhx.apicore.util;

import com.dhx.apicore.model.enums.InterfaceCategoryEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adorabled4
 * @className CategoryUtil
 * @date : 2023/12/26/ 10:28
 **/
public class CategoryBitMapUtil {

    /**
     * 获取类别值对应的位
     *
     * @param category 类别
     * @return int
     */
    public static long getCategoryValue(InterfaceCategoryEnum category) {
        return 1L << (category.getIndex() - 1);
    }


    /**
     * 解析类别值对应的位，返回类别列表
     *
     * @param categoryValue 类别值
     * @return {@link List}<{@link InterfaceCategoryEnum}>
     */
    public static List<InterfaceCategoryEnum> parseCategoryValue(long categoryValue) {
        List<InterfaceCategoryEnum> categories = new ArrayList<>();
        for (InterfaceCategoryEnum category : InterfaceCategoryEnum.values()) {
            long categoryBit = getCategoryValue(category);
            if ((categoryValue & categoryBit) != 0) {
                categories.add(category);
            }
        }
        return categories;
    }


    /**
     * 获取多个类别对应的位的值
     *
     * @param categories 类别
     * @return int
     */
    public static long getCombinedCategoryValue(List<InterfaceCategoryEnum> categories) {
        if(categories==null || categories.size()==0){
            return 0L;
        }
        long combinedValue = 0;
        for (InterfaceCategoryEnum category : categories) {
            combinedValue |= getCategoryValue(category);
        }
        return combinedValue;
    }

}
