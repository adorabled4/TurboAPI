package com.dhx.apicore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicore.model.DO.CallBack;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.model.query.AddCalLBackQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.service.CallBackService;
import com.dhx.apicore.mapper.CallBackMapper;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicommon.util.ThrowUtil;
import com.dhx.apicore.util.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dhx
 * @description 针对表【call_back】的数据库操作Service实现
 * @createDate 2024-01-01 14:18:57
 */
@Service
public class CallBackServiceImpl extends ServiceImpl<CallBackMapper, CallBack>
        implements CallBackService {

    @Resource
    InterfaceInfoService interfaceInfoService;

    @Override
    public void addCallBackConfig(AddCalLBackQuery query) {
        UserDTO user = UserHolder.getUser();
        // 校验接口是否支持
        interfaceInfoService.checkAsyncAPI(query.getInterfaceId());
        CallBack callBack = new CallBack(query);
        callBack.setUserId(user.getUserId());
        // 创建配置
        ThrowUtil.throwIf(!save(callBack), ErrorCode.OPERATION_ERROR, "创建回调地址配置失败!");
    }

    @Override
    public Page<CallBack> listConfigs(PageQuery query) {
        return query()
                .eq("user_id", UserHolder.getUser().getUserId())
                .page(new Page<CallBack>(query.getCurrentPage(), query.getPageSize()));
    }

    @Override
    public CallBack getCallBackConfig(Long interfaceId, Long userId) {
        List<CallBack> callBacks = query()
                .eq("user_id", userId)
                .eq("interface_id", interfaceId)
                .list();
        ThrowUtil.throwIf(callBacks.isEmpty(),ErrorCode.NULL_ERROR,"未进行接口回调地址配置!");
        return callBacks.get(0);
    }
}




