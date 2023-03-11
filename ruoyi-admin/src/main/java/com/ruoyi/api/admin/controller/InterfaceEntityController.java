package com.ruoyi.api.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.api.admin.domain.InterfaceEntity;
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
import com.ruoyi.api.admin.service.IInterfaceEntityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接口Controller
 * 
 * @author adorabled4
 * @date 2023-03-10
 */
@RestController
@RequestMapping("/admin/interfaceEntity")
public class InterfaceEntityController extends BaseController
{
    @Autowired
    private IInterfaceEntityService interfaceEntityService;

    /**
     * 查询接口列表
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceEntity:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterfaceEntity interfaceEntity)
    {
        startPage();
        List<InterfaceEntity> list = interfaceEntityService.selectInterfaceEntityList(interfaceEntity);
        return getDataTable(list);
    }

    /**
     * 导出接口列表
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceEntity:export')")
    @Log(title = "接口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterfaceEntity interfaceEntity)
    {
        List<InterfaceEntity> list = interfaceEntityService.selectInterfaceEntityList(interfaceEntity);
        ExcelUtil<InterfaceEntity> util = new ExcelUtil<InterfaceEntity>(InterfaceEntity.class);
        util.exportExcel(response, list, "接口数据");
    }

    /**
     * 获取接口详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceEntity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interfaceEntityService.selectInterfaceEntityById(id));
    }

    /**
     * 新增接口
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceEntity:add')")
    @Log(title = "接口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterfaceEntity interfaceEntity)
    {
        return toAjax(interfaceEntityService.insertInterfaceEntity(interfaceEntity));
    }

    /**
     * 修改接口
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceEntity:edit')")
    @Log(title = "接口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterfaceEntity interfaceEntity)
    {
        return toAjax(interfaceEntityService.updateInterfaceEntity(interfaceEntity));
    }

    /**
     * 删除接口
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceEntity:remove')")
    @Log(title = "接口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interfaceEntityService.deleteInterfaceEntityByIds(ids));
    }
}
