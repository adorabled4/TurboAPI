package com.dhx.apiinterface.domain.judge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author adorabled4
 * @className ExecuteCodeResponse
 * @date : 2024/01/02/ 15:21
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {
    /**
     * 执行信息
     */
    private String msg;
    /**
     * 执行状态
     */
    private Integer code;
    /**
     * 执行结果
     */
    private List<ExecuteResult> results;

}