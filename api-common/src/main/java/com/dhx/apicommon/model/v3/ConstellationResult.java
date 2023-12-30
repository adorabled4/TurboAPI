package com.dhx.apicommon.model.v3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author adorabled4
 * @className ConstellationResult
 * @date : 2023/12/30/ 19:39
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ConstellationResult {

    /** 宝瓶星座 */
    private String bx;
    /** 个性描述 */
    private String gxmd;
    /** 图标 */
    private String icon;
    /** 金牛座的描述 */
    private String jbtz;
    /** 吉祥物 */
    private String jssw;
    /** 家庭特质 */
    private String jttz;
    /** 控制因素 */
    private String kyjs;
    /** 星座名称 */
    private String name;
    /** 强项点 */
    private String qd;
    /** 起讫日期 */
    private String range;
    /** 所属属性 */
    private String sssx;
    /** 行事方式 */
    private String xsfg;
    /** 星语和花名 */
    private String xyhm;
    /** 幸运颜色 */
    private String xyys;
    /** 缘定 */
    private String yd;
    /** 阴阳属性 */
    private String yysx;
    /** 主导特质 */
    private String zdtz;
    /** 主管宫位 */
    private String zggw;
    /** 主管星星 */
    private String zgxx;
    /** 总结 */
    private String zj;
    /** 性格特点 */
    private String zxtd;

}
