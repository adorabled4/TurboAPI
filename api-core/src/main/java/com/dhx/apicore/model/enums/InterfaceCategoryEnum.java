package com.dhx.apicore.model.enums;

import lombok.AllArgsConstructor;

/**
 * @author adorabled4
 * @className InterfaceCategoryEnum
 * @date : 2023/12/26/ 09:50
 **/
@AllArgsConstructor
public enum InterfaceCategoryEnum {
    LIFE_SERVICE(1, "生活服务"),
    FINTECH(2, "金融科技"),
    DATA_INTELLIGENCE(3, "数据智能"),
    BUSINESS_ENTERPRISE(4, "企业工商"),
    TRANSPORTATION_GEOGRAPHY(5, "交通地理"),
    APP_DEVELOPMENT(6, "应用开发"),
    E_COMMERCE(7, "电子商务"),
    RECHARGE_PAYMENT(8, "充值缴费"),
    ENTERTAINMENT_VIDEO(9, "文娱视频"),
    FREE_API(10, "免费接口"),
    BUSINESS_SPECIFIC(11, "企业专用"),
    NEW_CUSTOMER_SPECIAL_OFFER(12, "新客首购特惠"),
    ONE_MINUTE_PURCHASE_ZONE(13, "一分购专区"),
    LIMIT_FREE(14, "限时免费");

    private int index;
    private String value;

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
