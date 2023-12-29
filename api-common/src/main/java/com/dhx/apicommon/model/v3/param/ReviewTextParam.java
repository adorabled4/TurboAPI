package com.dhx.apicommon.model.v3.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author adorabled4
 * @className ReviewTextParam
 * @date : 2023/12/29/ 15:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewTextParam {

    @Length(min = 2, max = 4096)
    private String text;
}
