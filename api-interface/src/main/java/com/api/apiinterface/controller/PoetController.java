package com.api.apiinterface.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.api.apicommon.common.BaseResponse;
import com.api.apiinterface.vo.PoetVO;
//import org.springframework.security.access.prepost.PreAuthorize;
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
import com.api.apiinterface.domain.Poet;
import com.api.apiinterface.service.IPoetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 随机诗词Controller
 * 
 * @author adorabled4
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/apiinterface/poet")
public class PoetController extends BaseController
{
    @Autowired
    private IPoetService poetService;

    @GetMapping("/random")
    public BaseResponse<PoetVO> getRandomPoet(){
        long total = poetService.getTotal();
        long id = (long) (Math.random()*total+1);
        while(id<0 || id >= total){
            id = (long) (Math.random()*total+1);
        }
        return poetService.getPoetVO(id);
    }

    /**
     * 查询随机诗词列表
     */
//    @PreAuthorize("@ss.hasPermi('apiinterface:poet:list')")
    @GetMapping("/list")
    public TableDataInfo list(Poet poet)
    {
        startPage();
        List<Poet> list = poetService.selectPoetList(poet);
        return getDataTable(list);
    }

    /**
     * 导出随机诗词列表
     */
//    @PreAuthorize("@ss.hasPermi('apiinterface:poet:export')")
    @Log(title = "随机诗词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Poet poet)
    {
        List<Poet> list = poetService.selectPoetList(poet);
        ExcelUtil<Poet> util = new ExcelUtil<Poet>(Poet.class);
        util.exportExcel(response, list, "随机诗词数据");
    }

    /**
     * 获取随机诗词详细信息
     */
//    @PreAuthorize("@ss.hasPermi('apiinterface:poet:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(poetService.selectPoetById(id));
    }

    /**
     * 新增随机诗词
     */
//    @PreAuthorize("@ss.hasPermi('apiinterface:poet:add')")
    @Log(title = "随机诗词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Poet poet)
    {
        return toAjax(poetService.insertPoet(poet));
    }

    /**
     * 修改随机诗词
     */
//    @PreAuthorize("@ss.hasPermi('apiinterface:poet:edit')")
    @Log(title = "随机诗词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Poet poet)
    {
        return toAjax(poetService.updatePoet(poet));
    }

    /**
     * 删除随机诗词
     */
//    @PreAuthorize("@ss.hasPermi('apiinterface:poet:remove')")
    @Log(title = "随机诗词", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(poetService.deletePoetByIds(ids));
    }
}
