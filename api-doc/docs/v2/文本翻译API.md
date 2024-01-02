---
sidebar_position: 1

---

![](http://dhx-blog.oss-cn-beijing.aliyuncs.com/dhx/translation.png)
# 文本翻译API

### 接口信息

:::info

**文本翻译API,输入需要翻译的文本内容, 智能分析出需要特别处理的专业词汇等内容，异步返回翻译的结果。）**

- 接口状态:  `可用`
- 请求方式: `POST`
- 消耗积分数: `1`
- Tags: `数据智能` `应用开发` `限时免费` 

:::
### 请求地址

```
http://turboapi.dhx.icuapi/v2/translate
```

### 请求参数

```json
{`text`:`string`}
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
    "text":"Spring Cloud Gateway features:\nBuilt on Spring Framework and Spring Boot\nAble to match routes on any request attribute.\nPredicates and filters are specific to routes.\nCircuit Breaker integration.\nSpring Cloud DiscoveryClient integration\nEasy to write Predicates and Filters\nRequest Rate Limiting\nPath Rewriting"
}
```

### 响应示例

```json
{"code":200,"data":"### 直译\nSpring Cloud Gateway 特性：\n- 基于 Spring Framework 和 Spring Boot 构建\n- 能够根据任何请求属性匹配路由。\n- 路由特定的谓词和过滤器。\n- 断路器集成。\n- Spring Cloud DiscoveryClient 集成\n- 易于编写谓词和过滤器\n- 请求速率限制\n- 路径重写\n\n***\n### 问题\n- “基于”在中文中更常表述为“构建于”或“依托于”以符合中文表达习惯。\n- “路由特定的谓词和过滤器”语句不通顺，可理解为“针对特定路由设计的谓词与过滤器功能”。\n- “断路器集成”、“Spring Cloud DiscoveryClient 集成”，对于非专业读者可能不易理解，需要在意译时给出解释。\n\n***\n### 意译\n```\nSpring Cloud Gateway 特性：\n- 依托于 Spring Framework 和 Spring Boot 构建\n- 可灵活配置，能够根据任意请求属性精准匹配路由。\n- 提供针对特定路由设计的谓词与过滤器功能（如条件判断与数据处理）\n- 集成了断路器机制，实现服务容错和自我保护\n- 完美整合 Spring Cloud DiscoveryClient，支持服务发现\n- 易于自定义扩展，方便开发者编写谓词和过滤器\n- 内置请求速率限制功能，有效控制访问流量\n- 支持路径重写，灵活应对服务路由策略调整\n```","message":"ok","traceId":"0d73201eb2a7407cba607b572b35a5ef"}
```
