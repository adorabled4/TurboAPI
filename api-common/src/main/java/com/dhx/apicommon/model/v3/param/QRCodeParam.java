package com.dhx.apicommon.model.v3.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author adorabled4
 * @className QRCodeParam
 * @date : 2023/12/30/ 19:55
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class QRCodeParam {

    /**
     * 二维码内容
     */
    private String text;

    /**
     * 纠错等级，可用值：h\q\m\l，例如：h
     */
    private String el;

    /**
     * 背景色代码，例如：ffffff
     */
    private String bgcolor;

    /**
     * 前景色代码，例如：000000
     */
    private String fgcolor;

    /**
     * logo图片URL地址或base64encode编码的图片内容，需要urlencode
     */
    private String logo;

    /**
     * 尺寸大小（像素），例如：300
     */
    private Integer w;

    /**
     * 边距大小（像素），例如：10
     */
    private Integer m;

    /**
     * logo宽度（像素），例如：60
     */
    private Integer lw;

    /**
     * 返回模式，1:二维码图片以base64encode编码返回 2:直接返回二维码图像，默认1
     */
    private Integer type;

}
