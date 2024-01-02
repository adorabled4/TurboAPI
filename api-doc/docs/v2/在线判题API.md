---
sidebar_position: 1

---

![](http://dhx-blog.oss-cn-beijing.aliyuncs.com/dhx/oj.png)
# 在线判题API

### 接口信息

:::info

**判题API,输入代码以及输入(input), 返回程序运行结果以及消耗内存,运行时间等信息。(测试期间,目前仅支持Java)**

- 接口状态:  `可用`
- 请求方式: `POST`
- 消耗积分数: `1`
- Tags: `数据智能` `应用开发` `限时免费` 

:::
### 请求地址

```
http://turboapi.dhx.icuapi/v2/judge/java
```

### 请求参数

```json
{`code`:`string`,`input`:`string[]`,`language`:`string`}
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
    "text":"public class Main { public static void main(String[] args) { int a = Integer.parseInt(args[0]); int b = Integer.parseInt(args[1]); Systemout.println(\"结果:\" + (a + b)); }}",
    "language":"Java",
    "input":["1 2"]
}
```

### 响应示例

```json
{
    "code": 200,
    "data": 3,
    "message": "ok"
}
```
