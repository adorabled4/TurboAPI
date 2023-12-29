package com.dhx.apiinterface.manager;

import com.dhx.apicommon.model.v3.ReviewTextResult;
import com.dhx.apiinterface.util.TextReview.WordFilter;
import org.springframework.stereotype.Component;

/**
 * @author adorabled4
 * @className ReviewManager
 * @date : 2023/12/29/ 15:30
 **/
@Component
public class ReviewManager {

    /**
     * 过滤文本内容中的敏感词
     *
     * @param text 文本
     * @return {@link ReviewTextResult}
     */
    public ReviewTextResult doFilter(String text) {
        String result;
        result = WordFilter.doFilter(text);
        return ReviewTextResult.builder().count(text.length()).handledText(result).isContains(WordFilter.isContains(text)).build();
    }
}
