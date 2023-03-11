package com.ruoyi.api.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.api.admin.domain.InterfaceExampleEntity;
import com.ruoyi.api.admin.service.IInterfaceExampleEntityService;
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
 * 接口示例Controller
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/admin/interfaceExampleEntity")
public class InterfaceExampleEntityController extends BaseController
{
    @Autowired
    private IInterfaceExampleEntityService interfaceExampleEntityService;

    /**
     * 查询接口示例列表
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceExampleEntity:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterfaceExampleEntity interfaceExampleEntity)
    {
        startPage();
        List<InterfaceExampleEntity> list = interfaceExampleEntityService.selectInterfaceExampleEntityList(interfaceExampleEntity);
        return getDataTable(list);
    }

    /**
     * 导出接口示例列表
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceExampleEntity:export')")
    @Log(title = "接口示例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterfaceExampleEntity interfaceExampleEntity)
    {
        List<InterfaceExampleEntity> list = interfaceExampleEntityService.selectInterfaceExampleEntityList(interfaceExampleEntity);
        ExcelUtil<InterfaceExampleEntity> util = new ExcelUtil<InterfaceExampleEntity>(InterfaceExampleEntity.class);
        util.exportExcel(response, list, "接口示例数据");
    }

    /**
     * 获取接口示例详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceExampleEntity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interfaceExampleEntityService.selectInterfaceExampleEntityById(id));
    }

    /**
     * 新增接口示例
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceExampleEntity:add')")
    @Log(title = "接口示例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterfaceExampleEntity interfaceExampleEntity)
    {
        return toAjax(interfaceExampleEntityService.insertInterfaceExampleEntity(interfaceExampleEntity));
    }

    /**
     * 修改接口示例
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceExampleEntity:edit')")
    @Log(title = "接口示例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterfaceExampleEntity interfaceExampleEntity)
    {
        return toAjax(interfaceExampleEntityService.updateInterfaceExampleEntity(interfaceExampleEntity));
    }

    /**
     * 删除接口示例
     */
    @PreAuthorize("@ss.hasPermi('admin:interfaceExampleEntity:remove')")
    @Log(title = "接口示例", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interfaceExampleEntityService.deleteInterfaceExampleEntityByIds(ids));
    }
}
