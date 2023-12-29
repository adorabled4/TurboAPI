package com.dhx.apicommon.model.v2.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


/**
 * @author adorabled4
 * @className TakeCommentParam
 * @date : 2023/12/29/ 19:40
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TakeCommentParam {

    /**
     * 菜品
     */
    @Length(min = 1,max = 48)
    private String recipe;
}
