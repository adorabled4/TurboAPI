package com.ruoyi.api.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.api.admin.domain.WhiteList;
import com.ruoyi.api.admin.service.IWhiteListService;
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
 * 白名单Controller
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/admin/list")
public class WhiteListController extends BaseController
{
    @Autowired
    private IWhiteListService whiteListService;

    /**
     * 查询白名单列表
     */
    @PreAuthorize("@ss.hasPermi('admin:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(WhiteList whiteList)
    {
        startPage();
        List<WhiteList> list = whiteListService.selectWhiteListList(whiteList);
        return getDataTable(list);
    }

    /**
     * 导出白名单列表
     */
    @PreAuthorize("@ss.hasPermi('admin:list:export')")
    @Log(title = "白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WhiteList whiteList)
    {
        List<WhiteList> list = whiteListService.selectWhiteListList(whiteList);
        ExcelUtil<WhiteList> util = new ExcelUtil<WhiteList>(WhiteList.class);
        util.exportExcel(response, list, "白名单数据");
    }

    /**
     * 获取白名单详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:list:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(whiteListService.selectWhiteListById(id));
    }

    /**
     * 新增白名单
     */
    @PreAuthorize("@ss.hasPermi('admin:list:add')")
    @Log(title = "白名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WhiteList whiteList)
    {
        return toAjax(whiteListService.insertWhiteList(whiteList));
    }

    /**
     * 修改白名单
     */
    @PreAuthorize("@ss.hasPermi('admin:list:edit')")
    @Log(title = "白名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WhiteList whiteList)
    {
        return toAjax(whiteListService.updateWhiteList(whiteList));
    }

    /**
     * 删除白名单
     */
    @PreAuthorize("@ss.hasPermi('admin:list:remove')")
    @Log(title = "白名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(whiteListService.deleteWhiteListByIds(ids));
    }
}
