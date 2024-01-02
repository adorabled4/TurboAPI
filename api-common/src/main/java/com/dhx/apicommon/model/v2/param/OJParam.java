package com.dhx.apicommon.model.v2.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author adorabled4
 * @className OJParam
 * @date : 2024/01/02/ 19:08
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OJParam {

    /**
     * 代码
     */
    private String code;

    /**
     * 输入
     */
    private List<String> input;

    /**
     * (预计)输出
     */
    @Nullable
    private List<String> output;

    /**
     * 语言
     */
    private String language;
}
