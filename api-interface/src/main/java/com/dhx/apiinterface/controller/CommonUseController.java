package com.dhx.apiinterface.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.ComputerSuffix;
import com.dhx.apiinterface.domain.LovelornSentence;
import com.dhx.apiinterface.service.ComputerSuffixService;
import com.dhx.apiinterface.service.LovelornSentenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author adorabled4
 * @className CommonUseController
 * @date : 2023/04/30/ 17:38
 **/
@RestController
@RequestMapping("/common")
public class CommonUseController {
    @Resource
    ComputerSuffixService computerSuffixService;

    @Resource
    LovelornSentenceService loveSentenceService;


    /**
     * 查询 文件后缀名 介绍
     * @param suffix
     * @return
     */
    @GetMapping("/suffix")
    public BaseResponse<ComputerSuffix> getSuffixDetail(@RequestParam("suffix")String suffix){
        QueryWrapper<ComputerSuffix> wrapper = new QueryWrapper<>();
        wrapper.eq("suffix",suffix);
        List<ComputerSuffix> list = computerSuffixService.list(wrapper);
        if(list==null || list.size()==0){
            return  ResultUtil.error(ErrorCode.PARAMS_ERROR,"未知的文件后缀~");
        }
        return ResultUtil.success(list.get(0));
    }

    /**
     * 随机 失恋 文案
     * @return
     */
    @GetMapping("/suffix")
    public BaseResponse<LovelornSentence> getSuffixDetail(){
        long total = loveSentenceService.count();
        long id = (long) (Math.random()*total+1);
        while(id<0 || id >= total){
            id = (long) (Math.random()*total+1);
        }
        // id 不存在,  返回一号
        if(loveSentenceService.list(new QueryWrapper<LovelornSentence>().eq("id",id)).size()==0){
            return ResultUtil.success(loveSentenceService.getById(1));
        }
        // 正常返回
        return ResultUtil.success(loveSentenceService.getById(id));
    }
}
