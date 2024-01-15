package com.dhx.apicommon.model.v2.param;

import com.dhx.apicommon.model.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author adorabled4
 * @className OCRParam
 * @date : 2024/01/14/ 23:26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OCRParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * base64格式编码的图像文件
     */
    @NotNull
    private String base64File;

    /**
     * 需要识别的语言
     */
    private LanguageEnum language;
    /**
     * 图像后缀
     */
    @Pattern(regexp = "^(png|jpg|jpeg)$")
    private String suffix;

}
