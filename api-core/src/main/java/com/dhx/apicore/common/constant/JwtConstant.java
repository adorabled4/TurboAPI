package com.dhx.apicore.common.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author adorabled4
 * @className JwtConstant
 * @date : 2023/04/10/ 14:57
 **/
public class JwtConstant {

    public static final  String SECRET_KEY ="64gbe45fgw46765rfdfbd";

    public static final  long EXPIRATION_TIME=60*60*24; //单位 second
}
