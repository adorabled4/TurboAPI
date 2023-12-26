package com.dhx.apicore.service;

import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.util.CategoryBitMapUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.dhx.apicore.model.enums.InterfaceCategoryEnum.*;

/**
 * @author adorabled4
 * @className InterfaceTest
 * @date : 2023/12/26/ 10:28
 **/
@SpringBootTest
public class InterfaceTest {


    @Resource
    InterfaceInfoService interfaceInfoService;

    @Test
    public void bitMapTest() {
        // 通过值解析出分类
        long value = CategoryBitMapUtil.getCategoryValue(LIFE_SERVICE);
        List<InterfaceCategoryEnum> parsedCategories = CategoryBitMapUtil.parseCategoryValue(value);
        assert Objects.equals(parsedCategories.get(0).getValue(), LIFE_SERVICE.getValue());
        // 通过分类给出值
        List<InterfaceCategoryEnum> selectedCategories = new ArrayList<>();
        selectedCategories.add(FINTECH);
        selectedCategories.add(ONE_MINUTE_PURCHASE_ZONE);
        selectedCategories.add(BUSINESS_SPECIFIC);
        long combinedValue = CategoryBitMapUtil.getCombinedCategoryValue(selectedCategories);
        assert combinedValue == Math.pow(2,FINTECH.getIndex())+ Math.pow(2,ONE_MINUTE_PURCHASE_ZONE.getIndex())+ Math.pow(2,BUSINESS_SPECIFIC.getIndex());
    }
}
