package com.dhx.apicore.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author adorabled4
 * @className PageQuery
 * @date : 2023/12/25/ 12:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {
    @Range(min = 1,max = 100, message = "页数应该在1~100之间")
    private Integer currentPage = 1;
    @Range(min = 1,max = 100, message = "一页元素数量应该在1~100之间")
    private Integer pageSize = 10;
}
