---
sidebar_position: 1

---

![](http://dhx-blog.oss-cn-beijing.aliyuncs.com/dhx/locate.png)
# 全国行政区划查询API

### 接口信息

:::info

**全国行政区划(省/市/区/县)查询API部署包，支持省、市、区县、乡镇查询，最大为4级。**

- 接口状态:  `可用`
- 请求方式: `GET`
- 消耗积分数: `1`
- Tags: `数据智能` `文娱视频` `免费接口` `限时免费` 

:::
### 请求地址

```
https://turboapi.dhx.icu/api/v3/jxzqh
```

### 请求参数

```json
{'fid': 'string'}
```

### 响应参数

|  参数名称   |  类型  |  描述  |
| :---------: | :----: |:----:|
|    code     |  int   | 状态码  |
|    data     | string | 响应数据 |
|   message   | string | 响应描述 |

### 请求示例

```json
{
    "fid":370403
}
```

### 响应示例

```json
{
    "code": 200,
    "data": [
        {
            "id": "370403001",
            "name": "临城街道",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403002",
            "name": "常庄街道",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403003",
            "name": "兴城街道",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403004",
            "name": "张范街道",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403005",
            "name": "兴仁街道",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403101",
            "name": "沙沟镇",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403102",
            "name": "周营镇",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403103",
            "name": "邹坞镇",
            "fid": "370403",
            "level_id": "4"
        },
        {
            "id": "370403104",
            "name": "陶庄镇",
            "fid": "370403",
            "level_id": "4"
        }
    ],
    "message": "ok"
}
```
