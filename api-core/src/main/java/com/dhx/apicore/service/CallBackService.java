package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhx.apicore.model.DO.CallBack;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicore.model.query.AddCalLBackQuery;
import com.dhx.apicore.model.query.PageQuery;


/**
* @author dhx
* @description 针对表【call_back】的数据库操作Service
* @createDate 2024-01-01 14:18:57
*/
public interface CallBackService extends IService<CallBack> {

    void addCallBackConfig(AddCalLBackQuery query);

    Page<CallBack> listConfigs(PageQuery query);
}
