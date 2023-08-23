package com.dhx.apiinterface.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.constant.RedisConstant;
import com.dhx.apiinterface.vo.PoetVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.dhx.apiinterface.mapper.PoetMapper;
import com.dhx.apiinterface.domain.Poet;
import com.dhx.apiinterface.service.IPoetService;

import javax.annotation.Resource;

/**
 * 诗句Service业务层处理
 *
 * @author adorabled4
 * @date 2023-03-11
 */
@Service
public class PoetServiceImpl implements IPoetService {
    @Autowired
    private PoetMapper poetMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public long getTotal() {
        return poetMapper.getTotal();
    }

    @Override
    public BaseResponse<PoetVO> getPoetVO(long id) {
        String key = RedisConstant.RANDOM_POET_KEY + id;
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(s)) {
            // 写入redis
            Poet poet = poetMapper.selectPoetById(id);
            while (poet == null) {
                poet = poetMapper.selectPoetById(1L);
            }
            PoetVO poetVO = BeanUtil.copyProperties(poet, PoetVO.class);
            s = JSONUtil.toJsonStr(poetVO);
            stringRedisTemplate.opsForValue().set(key, s, RedisConstant.RANDOM_POET_TTL, TimeUnit.SECONDS);
            return ResultUtil.success(poetVO);
        } else {
            PoetVO poetVO = JSONUtil.toBean(s, PoetVO.class);
            return ResultUtil.success(poetVO);
        }
    }

    @Override
    public BaseResponse<PoetVO> getRandomPoetVo() {
        long total = this.getTotal();
        long id = (long) (Math.random()*total+1);
        while(id<0 || id >= total){
            id = (long) (Math.random()*total+1);
        }
        return this.getPoetVO(id);
    }

    /**
     * 查询诗句
     *
     * @param id 诗句主键
     * @return 诗句
     */
    @Override
    public Poet selectPoetById(Long id) {
        return poetMapper.selectPoetById(id);
    }

    /**
     * 查询诗句列表
     *
     * @param poet 诗句
     * @return 诗句
     */
    @Override
    public List<Poet> selectPoetList(Poet poet) {
        return poetMapper.selectPoetList(poet);
    }

    /**
     * 新增诗句
     *
     * @param poet 诗句
     * @return 结果
     */
    @Override
    public int insertPoet(Poet poet) {
        return poetMapper.insertPoet(poet);
    }

    /**
     * 修改诗句
     *
     * @param poet 诗句
     * @return 结果
     */
    @Override
    public int updatePoet(Poet poet) {
        return poetMapper.updatePoet(poet);
    }

    /**
     * 批量删除诗句
     *
     * @param ids 需要删除的诗句主键
     * @return 结果
     */
    @Override
    public int deletePoetByIds(Long[] ids) {
        return poetMapper.deletePoetByIds(ids);
    }

    /**
     * 删除诗句信息
     *
     * @param id 诗句主键
     * @return 结果
     */
    @Override
    public int deletePoetById(Long id) {
        return poetMapper.deletePoetById(id);
    }
}
