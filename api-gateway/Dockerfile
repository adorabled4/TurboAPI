FROM openjdk:8-jdk-alpine
LABEL maintainer="adorabled4 <dhx2648466390@163.com>"

# 设置时区
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apk del tzdata

# 创建工作目录
RUN mkdir -p /app/api-gateway

# 将所有jar文件添加到对应模块的目录中 => 需要注意的是 , TODO 构建的时候是以dockerfile所在的目录开始的
COPY jar/api-gateway.jar /app/api-gateway

# 暴露端口号 (gateway)
EXPOSE 88

# 运行所有jar文件
CMD ["sh", "-c", "java -jar /app/api-gateway/api-gateway.jar --spring.profiles.active=test"]