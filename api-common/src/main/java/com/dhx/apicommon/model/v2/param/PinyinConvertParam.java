package com.dhx.apicommon.model.v2.param;

import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * @author adorabled4
 * @className PinyinConvertParam
 * @date : 2024-1-6 17:34:21
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PinyinConvertParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 代码
     */
    @Length(min = 1, max = 4096, message = "长度非法!")
    private String text;

    /**
     * 类型
     */
    private PinyinStyleEnum type;
}
