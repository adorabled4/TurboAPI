---
sidebar_position: 1

---

![](http://dhx-blog.oss-cn-beijing.aliyuncs.com/dhx/qrcode.png)
# 生成二维码API

### 接口信息

:::info

**二维码生成API，支持文本、背景色、尺寸、边距等参数设置。**

- 接口状态:  `可用`
- 请求方式: `GET`
- 消耗积分数: `1`
- Tags: `数据智能` `文娱视频` `免费接口` `限时免费` 

:::
### 请求地址

```
https://turboapi.dhx.icu/api/v3/jqrcode
```

### 请求参数

```json
{'key': 'string','text': 'string','el': 'string','bgcolor': 'string','fgcolor': 'string','logo': 'string','w': 'int','m': 'int','lw': 'int','type': 'int'}
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
  "text": "你好啊",
  "el": "h",
  "bgcolor": "ffffff",
  "fgcolor": "000000",
  "logo": "https://example.com/logo.png",
  "w": 300,
  "m": 10,
  "lw": 60,
  "type": 1
}
```

### 响应示例

```json
{
    "code": 200,
    "data": {
        "base64_image": "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEAAQMAAABmvDolAAAABlBMVEX///8AAABVwtN+AAABGUlEQVR42uyYMZKEIBREP2VgyBE4Ckdzj8ZROAKhgWVvfRhZZcUNd2rojtR6URf/CQjDMMy7x6IkiEszdvN6XQm0QHlcgkeaNzE4fx0KmAEUQKbdQBYgEegDuqJykwSeAR/lj6pHB47ZjJId1R/ewYFD5j7adXqy/eBAjdfv+8M+4bMBi3XaRRYEp+1oUcEhEWgBjQG+PGJ9d2dHEWgcpY+HzC9VE/jV5LyZPJyudRSBy4YTL5kHHVgCDXCx/VZKjWKHA05//5/F1dkejA1UR+nZv+wP3FlRBO6Ocqbcl9hEoAuUWxQtFbEjMQK5SdEzb9HZ/e3B6EA989pVHZYvB+6G98OBKnOkWRUFIF5GjwDDMMw/5jsAAP//QKiHBJ0JEcAAAAAASUVORK5CYII="
    },
    "message": "ok"
}
```
