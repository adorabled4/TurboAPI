---
sidebar_position: 1

---

![](${imageUrl})
# ${name}

### 接口信息

:::info

**${description}**

- 接口状态:  `${status}`
- 请求方式: `${requestMethod}`
- 消耗积分数: `1`
- Tags: <#if categories?size != 0><#list categories as category>`${category}` </#list><#else>
    没有标签
</#if>


:::
### 请求地址

```
${serviceAddress}${callPath}
```

### 请求参数

<#if requestParam??>
```json
${requestParam}
```
<#else>
**暂无**
</#if>

### 响应参数

|  参数名称   |  类型  |  描述  |
| :---------: | :----: |:----:|
|    code     |  int   | 状态码  |
|    data     | string | 响应数据 |
|   message   | string | 响应描述 |

### 请求示例

<#if requestExample??>
```json
${requestExample}
```
<#else>
**暂无**
</#if>

### 响应示例

<#if responseExample??>
```json
${responseExample}
```
<#else>
**暂无**
</#if>
