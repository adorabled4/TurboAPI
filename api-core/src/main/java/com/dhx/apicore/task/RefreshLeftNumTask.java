package com.dhx.apicore.task;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhx.apicore.model.DO.UserInterfaceInfoEntity;
import com.dhx.apicore.model.enums.DayStatus;
import com.dhx.apicore.service.UserInterfaceInfoEntityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author adorabled4
 * @className RefreshLeftNumTask
 * @date : 2023/04/19/ 09:56
 **/
@Component
public class RefreshLeftNumTask {
    public static final Integer DEFAULT_FREE_LEFT_NUM = 100;
    public static final Integer DEFAULT_FREE_TOTAL_NUM = 100;

    public static final Integer REFRESH_PAGE_SIZE = 1000;
    @Resource
    ThreadPoolExecutor executor;
    @Resource
    UserInterfaceInfoEntityService userInterfaceInfoEntityService;

    /**
     * 页数
     */
    volatile long pageNum=1;

    /**
     * 当前的页码
     */
    volatile AtomicLong currentPage=new AtomicLong(1L);


    /**
     * 每天定时刷新用户的剩余接口调用次数
     */
    @Scheduled(cron = "0 0 0 * * ?") // 每天零点执行
    private void refreshTask(){
        // 分页查询
        Page<UserInterfaceInfoEntity> page = userInterfaceInfoEntityService.query()
                .eq("left_num",DEFAULT_FREE_LEFT_NUM)
                .page(new Page<>(1, REFRESH_PAGE_SIZE));
        pageNum = page.getPages(); // 获取页数
        executor.execute(()->{
            readAndRefresh();
        });
        pageNum =0;
    }
    private void readAndRefresh(){
        if(currentPage.longValue()>pageNum){
            return ;
        }
        ConcurrentMap<Long, UserInterfaceInfoEntity> concurrentMap = new ConcurrentHashMap<>();
        long currentPageNum = currentPage.getAndAdd(1);
        List<UserInterfaceInfoEntity> list = userInterfaceInfoEntityService.query().page(new Page<>(currentPageNum, REFRESH_PAGE_SIZE)).getRecords();
        // 将查询结果放入并发集合中
        for (UserInterfaceInfoEntity entity : list) {
            concurrentMap.put(entity.getId(), entity);
        }
        // 遍历并发集合，使用多线程更新数据
        concurrentMap.forEach( (id, entity) -> {
            executor.submit(() -> {
                userInterfaceInfoEntityService.updateById(
                    new UserInterfaceInfoEntity(entity.getUserId(), DayStatus.UNUSED)
                );
            });
        });
    }
}
