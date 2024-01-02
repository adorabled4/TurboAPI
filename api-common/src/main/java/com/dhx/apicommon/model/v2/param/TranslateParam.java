package com.dhx.apicommon.model.v2.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


/**
 * @author adorabled4
 * @className TranslateParam
 * @date : 2024-1-2 07:58:12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslateParam {

    /**
     * 菜品
     */
    @Length(min = 1,max = 3600)
    private String text;
}
