package com.dhx.apicore.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.BooleanUtil;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className RedisUtil
 * @date : 2023/04/23/ 21:40
 **/
@Data
public class RedisLock {

    /**
     * 不同的业务对应不同的锁的名称
     */
    private String name;

    /**
     * 锁的前缀
     */
    public static final String KEY_PREFIX="lock:";

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用来封装锁的value
     */
    public static final String ID_Prefix= UUID.randomUUID().toString(true)+"-";


    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        String path= "classpath:unlock.lua"; // 使用classpath前缀
        Resource resource = new ClassPathResource(path);
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            File file = File.createTempFile("unlock", ".lua");
            FileUtils.copyInputStreamToFile(inputStream, file);
            UNLOCK_SCRIPT.setLocation(resource);
            UNLOCK_SCRIPT.setResultType(Long.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RedisLock(){

    }
    public RedisLock(StringRedisTemplate stringRedisTemplate,String name){
        this.name=name;
        this.stringRedisTemplate=stringRedisTemplate;
    }

    /**
     * 加锁 , 参数单位为秒
     * @param timeOutSec
     * @return
     */
    public boolean tryLock(Long timeOutSec, TimeUnit timeUnit) {
        //获取当前线程的标识
        String threadId = ID_Prefix+Thread.currentThread().getId();
        String key= name +KEY_PREFIX;
        Boolean success=stringRedisTemplate.opsForValue().setIfAbsent(key, threadId,timeOutSec, timeUnit);
        //手动拆箱, 防止空指针  isTrue , 如果success 是null ,也会返回false;
        return BooleanUtil.isTrue(success);
    }

    /**
     * 调用lua脚本解锁
     */
    public void unLock() {
        //调用lua脚本 , 只有一行代码, 保证释放锁的原子性
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX+name),
                ID_Prefix+Thread.currentThread().getId()
        );
    }
}
