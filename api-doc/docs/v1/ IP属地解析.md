---
sidebar_position: 1

---

![](http://dhx-blog.oss-cn-beijing.aliyuncs.com/dhx/ipana.png)
#  IP属地解析

### 接口信息

:::info

**输入IPV4地址, 返回IP属地信息**

- 接口状态:  `可用`
- 请求方式: `GET`
- 消耗积分数: `1`
- Tags: `数据智能` `限时免费` 

:::
### 请求地址

```
https://turboapi.dhx.icu/api/v1/common/ip/ana
```

### 请求参数

```json
{`ip`:`string`}
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
    "ip":"43.132.44.14"
}
```

### 响应示例

```json
{
    "code": 200,
    "data": "澳大利亚|0|0|0|0",
    "message": "ok"
}
```
