package com.dhx.apiinterface.v2;

import com.dhx.apicommon.model.v3.ReviewTextResult;
import com.dhx.apiinterface.manager.ReviewManager;
import org.junit.jupiter.api.Test;

/**
 * @author adorabled4
 * @className ReviewTest
 * @date : 2023/12/29/ 15:54
 **/
public class ReviewTest {

    @Test
    public void textTest(){
        ReviewManager reviewManager = new ReviewManager();
        ReviewTextResult res = reviewManager.doFilter("胸&推小姐");
        assert res.isContains();
        assert res.getCount()==5;
    }
}
