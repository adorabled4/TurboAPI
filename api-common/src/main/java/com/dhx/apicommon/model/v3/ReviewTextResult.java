package com.dhx.apicommon.model.v3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author adorabled4
 * @className ReviewTextResult
 * @date : 2023/12/29/ 15:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewTextResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 处理后的内容
     */
    private String handledText;

    /**
     * 是否包含敏感词
     */
    private boolean isContains;

    /**
     * 处理的内容的长度
     */
    private int count;
}
