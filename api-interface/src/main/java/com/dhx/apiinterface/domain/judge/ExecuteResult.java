package com.dhx.apiinterface.domain.judge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author adorabled4
 * @className ExecuteResult
 * @date : 2024/01/02/ 15:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteResult {
    //退出码
    private Integer exitValue;
    //正常信息
    private String output;
    //错误信息
    private String errorOutput;
    //运行时间
    private Long time;
    //消耗内存
    private Long memory;
}
