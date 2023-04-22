package com.dhx.apicore.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicore.model.DO.UserInterfaceInfoEntity;
import com.dhx.apicore.service.UserInterfaceInfoEntityService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className InnerUserInterfaceInfoServiceImpl
 * @date : 2023/04/19/ 14:49
 **/
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    UserInterfaceInfoEntityService userInterfaceInfoEntityService;

    @Override
    public boolean invokeCount(Long userId, Long interfaceId) {
        Long count = userInterfaceInfoEntityService.query().eq("user_id", userId).eq("interface_id", interfaceId).count();
        if (count > 0) {
            UpdateWrapper<UserInterfaceInfoEntity> wrapper = new UpdateWrapper<>();
            wrapper.eq("user_id", userId).eq("interface_id", interfaceId);
            wrapper.setSql("left_num=left_num-1 , total_num=total_num+1");
            return userInterfaceInfoEntityService.update(wrapper);
        } else {
            // 插入新的数据
            UserInterfaceInfoEntity infoEntity = new UserInterfaceInfoEntity();
            infoEntity.setUserId(userId);
            infoEntity.setInterfaceId(interfaceId);
            infoEntity.setTotalNum(1);
            infoEntity.setLeftNum(100 - 1);
            infoEntity.setStatus(1);
            return userInterfaceInfoEntityService.save(infoEntity);
        }
    }

    @Override
    public int getUserLeftNum(Long userId, Long interfaceId) {
        UserInterfaceInfoEntity one = userInterfaceInfoEntityService.query().eq("user_id", userId).eq("interface_id", interfaceId).one();
        if (one.getLeftNum() == null || one.getLeftNum() <= 0) {
            return 0;
        }
        return one.getLeftNum();
    }
}
