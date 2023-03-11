package com.ruoyi.api.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.api.admin.domain.UserEntity;
import com.ruoyi.api.admin.service.IUserEntityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/admin/userEntity")
public class UserEntityController extends BaseController
{
    @Autowired
    private IUserEntityService userEntityService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('admin:userEntity:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserEntity userEntity)
    {
        startPage();
        List<UserEntity> list = userEntityService.selectUserEntityList(userEntity);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('admin:userEntity:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserEntity userEntity)
    {
        List<UserEntity> list = userEntityService.selectUserEntityList(userEntity);
        ExcelUtil<UserEntity> util = new ExcelUtil<UserEntity>(UserEntity.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:userEntity:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userEntityService.selectUserEntityByUserId(userId));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('admin:userEntity:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserEntity userEntity)
    {
        return toAjax(userEntityService.insertUserEntity(userEntity));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('admin:userEntity:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserEntity userEntity)
    {
        return toAjax(userEntityService.updateUserEntity(userEntity));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('admin:userEntity:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userEntityService.deleteUserEntityByUserIds(userIds));
    }
}
