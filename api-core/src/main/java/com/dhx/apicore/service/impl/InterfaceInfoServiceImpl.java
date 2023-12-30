package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.query.AddInterfaceInfoQuery;
import com.dhx.apicommon.service.api.ApiInterfaceService;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.common.constant.RedisConstant;
import com.dhx.apicore.config.FreeMarkerConfig;
import com.dhx.apicore.mapper.InterfaceInfoEntityMapper;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.model.DTO.InterfaceMetaDataDTO;
import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicommon.model.enums.InterfaceStatusEnum;
import com.dhx.apicore.model.query.InterfaceCategoryQuery;
import com.dhx.apicore.model.query.InterfaceIdsQuery;
import com.dhx.apicore.model.query.InterfaceUpdateQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVO;
import com.dhx.apicore.model.vo.InterfaceDetailVO;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import com.dhx.apicore.util.CategoryBitMapUtil;
import com.dhx.apicore.util.ThrowUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dhx
 * @description 针对表【t_interface_entity】的数据库操作Service实现
 * @createDate 2023-04-12 09:38:35
 */
@Service
@Slf4j
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoEntityMapper, InterfaceInfoEntity>
        implements InterfaceInfoService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @DubboReference
    ApiInterfaceService apiInterfaceService;

    @Resource
    Configuration cfg;

    @Resource
    FreeMarkerConfig freeMarkerConfig;

    @Override
    public List<InterfaceBasicInfoVO> getInterfaceList(PageQuery pageQuery) {
        Page<InterfaceInfoEntity> page = query().ne("status", InterfaceStatusEnum.BANNED).page(new Page<>(pageQuery.getCurrentPage(),
                pageQuery.getPageSize()));
        return page.getRecords().stream()
//                .filter(item -> item.getStatus() != InterfaceStatusEnum.DEVELOPING)
                .map(item -> {
                    InterfaceBasicInfoVO interfaceBasicInfoVO = BeanUtil.copyProperties(item, InterfaceBasicInfoVO.class);
                    // 设置分类信息
                    Long categoryBitMap = item.getCategoryBitMap();
                    List<InterfaceCategoryEnum> interfaceCategoryEnums = CategoryBitMapUtil.parseCategoryValue(categoryBitMap);
                    interfaceBasicInfoVO.setCategories(interfaceCategoryEnums);
                    return interfaceBasicInfoVO;
                }).collect(Collectors.toList());
    }


    @Override
    public BaseResponse<InterfaceDetailVO> getInterfaceDetail(Long id) {
        InterfaceInfoEntity interfaceEntity = findById(id);
        InterfaceVariableInfoEntity variableInfo = interfaceVariableInfoService.findById(id);
        InterfaceDetailVO interfaceDetailVO = BeanUtil.copyProperties(interfaceEntity, InterfaceDetailVO.class);
        BeanUtil.copyProperties(variableInfo, interfaceDetailVO);
        // 设置分类信息
        Long categoryBitMap = interfaceEntity.getCategoryBitMap();
        List<InterfaceCategoryEnum> interfaceCategoryEnums = CategoryBitMapUtil.parseCategoryValue(categoryBitMap);
        interfaceDetailVO.setCategories(interfaceCategoryEnums);
        return ResultUtil.success(interfaceDetailVO);
    }

    private InterfaceInfoEntity findById(Long id) {
        InterfaceInfoEntity interfaceInfo = getById(id);
        ThrowUtil.throwIf(interfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        return interfaceInfo;
    }

    @Override
    public void addRankScore(Long interfaceId) {
        stringRedisTemplate.opsForZSet().incrementScore(RedisConstant.INTERFACE_RANK_KEY, String.valueOf(interfaceId), 1);
    }

    @Override
    public BaseResponse<List<InterfaceRankInfoVo>> getRank5Interface() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(RedisConstant.INTERFACE_RANK_KEY, 0, 4);
        // 获取ids , 批量查询更快
        List<Long> ids = typedTuples.stream().map(item -> {
            String interfaceId = item.getValue();
            return Long.valueOf(interfaceId);
        }).collect(Collectors.toList());
        // 封装vo
        if (ids.size() >= 5) {
            List<InterfaceInfoEntity> interfaceEntities = listByIds(ids);
            List<InterfaceRankInfoVo> collect = interfaceEntities
                    .stream().map(item -> BeanUtil.copyProperties(item, InterfaceRankInfoVo.class))
                    .collect(Collectors.toList());
            return ResultUtil.success(collect);
        } else {
            // 从数据库中查询
            List<InterfaceInfoEntity> interfaceEntities = query()
                    .eq("status", InterfaceStatusEnum.AVAILABLE)
                    .orderByDesc("call_times")
                    .page(new Page<>(1, 5)).getRecords();
            List<InterfaceRankInfoVo> collect = interfaceEntities
                    .stream().map(item -> BeanUtil.copyProperties(item, InterfaceRankInfoVo.class))
                    .collect(Collectors.toList());
            return ResultUtil.success(collect);
        }
    }

    @Override
    @Transactional
    public void publishInterface(InterfaceUpdateQuery query) {
        InterfaceVariableInfoEntity variableInfo = BeanUtil.copyProperties(query, InterfaceVariableInfoEntity.class);
        InterfaceInfoEntity interfaceInfo = getInterfaceEntity(query);
        variableInfo.setModelName(query.getModelName());
        boolean update = save(interfaceInfo);
        variableInfo.setId(interfaceInfo.getId());
        boolean saveOrUpdate = interfaceVariableInfoService.saveOrUpdate(variableInfo);
        // 同步到 api-interface 的 接口meta-data中
        boolean syncResult = sync2ApiInterface(query, interfaceInfo.getId());
        ThrowUtil.throwIf(!update || !saveOrUpdate, ErrorCode.OPERATION_ERROR, "保存接口信息失败");
    }

    private boolean sync2ApiInterface(InterfaceUpdateQuery query, Long interfaceId) {
        AddInterfaceInfoQuery addInterfaceInfoQuery = AddInterfaceInfoQuery.builder()
                .interfaceName(query.getName())
                .isAigc(query.getIsAigc())
                .interfaceId(interfaceId)
                .callPath(query.getCallPath())
                .build();
        return apiInterfaceService.addInterfaceData(addInterfaceInfoQuery);
    }

    private InterfaceInfoEntity getInterfaceEntity(InterfaceUpdateQuery query) {
        InterfaceInfoEntity interfaceInfo = BeanUtil.copyProperties(query, InterfaceInfoEntity.class);
        List<InterfaceCategoryEnum> categories = query.getCategories();
        long combinedCategoryValue = CategoryBitMapUtil.getCombinedCategoryValue(categories);
        interfaceInfo.setCategoryBitMap(combinedCategoryValue);
        return interfaceInfo;
    }


    @Override
    public BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceByCategories(InterfaceCategoryQuery query) {
        List<InterfaceCategoryEnum> categories = query.getCategories();
        long categoryBitMapValue = CategoryBitMapUtil.getCombinedCategoryValue(categories);
        List<InterfaceBasicInfoVO> interfaceBasicInfoVOS = query()
                .eq("category_bit_map", categoryBitMapValue)
                .ne("status", InterfaceStatusEnum.BANNED)
                .page(new Page<>(query.getCurrentPage(), query.getPageSize())).getRecords()
                .stream().map(item -> {
                    InterfaceBasicInfoVO interfaceBasicInfoVO = BeanUtil.copyProperties(item, InterfaceBasicInfoVO.class);
                    // 设置分类信息
                    Long categoryBitMap = item.getCategoryBitMap();
                    List<InterfaceCategoryEnum> interfaceCategoryEnums = CategoryBitMapUtil.parseCategoryValue(categoryBitMap);
                    interfaceBasicInfoVO.setCategories(interfaceCategoryEnums);
                    return interfaceBasicInfoVO;
                }).collect(Collectors.toList());
        return ResultUtil.success(interfaceBasicInfoVOS);
    }

    @Override
    public void genInterfaceDocMD(InterfaceIdsQuery query) {
        try {
            List<InterfaceMetaDataDTO> templateDTOS = query.getIds()
                    .stream().map(this::getInterfaceTemplateData).collect(Collectors.toList());
            Template template = cfg.getTemplate("api-doc.md.ftl");
            for (InterfaceMetaDataDTO templateDTO : templateDTOS) {
                log.info(templateDTO.toString());
                generateDoc(template, templateDTO);
            }
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成接口文档
     *
     * @param template 模板
     * @param api      api
     * @throws IOException       ioexception
     * @throws TemplateException 模板异常
     */
    private void generateDoc(Template template, InterfaceMetaDataDTO api) throws IOException, TemplateException {
        String docPath = freeMarkerConfig.getDocPath();
        File folder = new File(docPath);
        if (!folder.exists()) {
            ThrowUtil.throwIf(!folder.mkdirs(), ErrorCode.SYSTEM_ERROR, "创建文件夸失败!");
        }
        String fileName = docPath + "/" + api.getVersion() + "/" + api.getName() +".md";
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(api, out);
        fos.close();
        out.close();
    }

    @Override
    public void genSDKCode(InterfaceIdsQuery query) {
        try {
            List<InterfaceMetaDataDTO> templateDTOS = query.getIds()
                    .stream().map(this::getInterfaceTemplateData).collect(Collectors.toList());
            Template template = cfg.getTemplate("api-sdk-client.java.ftl");
            generateSDKCode(template, templateDTOS);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateSDKCode(Template template, List<InterfaceMetaDataDTO> metaDataDTOS) throws IOException, TemplateException {
        String sdkPath = freeMarkerConfig.getSdkPath();
        File folder = new File(sdkPath);
        if (!folder.exists()) {
            ThrowUtil.throwIf(!folder.mkdirs(), ErrorCode.SYSTEM_ERROR, "创建文件夹失败!");
        }
        Map<String, Object> context = new HashMap<>();
        context.put("apis", metaDataDTOS);
        context.put("basePackage", "com.dhx.apisdk.client");
        context.put("className", "TurboAPIClient");
        context.put("time", DateUtil.format(DateTime.now(), "yyyy-MM-dd"));
        String fileName = sdkPath + "/" + "TurboAPIClient.java";
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(context, out);
        fos.close();
        out.close();
    }

    /**
     * 获取接口模板数据
     *
     * @param id id
     * @return {@link BaseResponse}<{@link InterfaceMetaDataDTO}>
     */
    private InterfaceMetaDataDTO getInterfaceTemplateData(Long id) {
        InterfaceInfoEntity interfaceEntity = findById(id);
        InterfaceVariableInfoEntity variableInfo = interfaceVariableInfoService.findById(id);
        InterfaceMetaDataDTO interfaceMetaDataDTO = new InterfaceMetaDataDTO();
        BeanUtil.copyProperties(interfaceEntity, interfaceMetaDataDTO);
        BeanUtil.copyProperties(variableInfo, interfaceMetaDataDTO);
        // 设置分类信息
        Long categoryBitMap = interfaceEntity.getCategoryBitMap();
        List<String> interfaceCategoryEnums = CategoryBitMapUtil.parse2String(categoryBitMap);
        interfaceMetaDataDTO.setStatus(interfaceEntity.getStatus().getName());
        interfaceMetaDataDTO.setCategories(interfaceCategoryEnums);
        // 设置version
        String version = variableInfo.getCallPath().substring(4, 6);
        interfaceMetaDataDTO.setVersion(version);
        return interfaceMetaDataDTO;
    }

    @Override
    public void updateInterfaceInfo(InterfaceUpdateQuery query) {
        InterfaceInfoEntity interfaceInfo = BeanUtil.copyProperties(query, InterfaceInfoEntity.class);
        InterfaceVariableInfoEntity variableInfo = BeanUtil.copyProperties(query, InterfaceVariableInfoEntity.class);
        interfaceInfo.setId(query.getInterfaceId());
        variableInfo.setId(query.getInterfaceId());
        boolean update = updateById(interfaceInfo);
        boolean saveOrUpdate = interfaceVariableInfoService.updateById(variableInfo);
        // 同步到 api-interface 的 接口meta-data中
        boolean syncResult = sync2ApiInterface(query, query.getInterfaceId());
        ThrowUtil.throwIf(!update || !saveOrUpdate || !syncResult, ErrorCode.OPERATION_ERROR, "保存接口信息失败");
    }

    @Override
    public void increaseCount(Long interfaceId) {
        boolean update = update().setSql("total_count = total_count + 1 ").eq("id", interfaceId).update();
        ThrowUtil.throwIf(!update, ErrorCode.SYSTEM_ERROR, "更新接口调用次数失败!");
    }
}




