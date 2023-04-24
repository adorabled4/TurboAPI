-- 锁的key
--local key = KEYS[1]
-- 当前线程标识
local threadId  = ARGV[1]

--获取锁中的线程标识
local id = redis.call('get',KEYS[1])

if(id == threadId )then
    -- 释放锁 del key
    return redis.call('del',KEYS[1])
end ;
return 0;