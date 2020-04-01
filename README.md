# COSS4-Server

![](https://img.shields.io/github/languages/code-size/cambridgejames/coss4-server)
![](https://img.shields.io/github/issues/cambridgejames/coss4-server)
![](https://img.shields.io/github/downloads/cambridgejames/coss4-server/total)
![](https://img.shields.io/github/v/tag/cambridgejames/coss4-server)
![](https://img.shields.io/github/license/cambridgejames/coss4-server)

> 竞赛在线评分系统 v4.0
>
> 说明：
>
> 1. 本系统为基于微服务架构的竞赛在线评分系统的后端项目，主要用于一般的比赛、答辩等需要评委打分并汇总排名的活动
> 2. 支持用户注册、创建竞赛、报名参赛、在线评分及汇总、导出结果、生成成绩证明等功能

## 构建

### 一、开发环境

在执行构建前，请确保下述开发环境已成功搭建，并正确配置好环境变量：

- Java: JDK 8u241
- Maven 3.6.1

### 二、构建方法

1. 执行下述代码将存储库克隆到本地环境
```lang=shell
git clone git@github.com:cambridgejames/coss4-server.git
cd coss4-server
```

2. 执行下述代码以运行单元测试
```
mvn test
```
   
3. 执行下述代码从源代码构建 jar 包
```lang=shell
# Windows 环境下执行以下代码进行构建
coss4 --build

# Linux 环境下执行以下代码进行构建
sudo chmod u+x ./coss4.sh
sudo ./coss4 --build
```

## 文档目录

### 一、API接口文档

竞赛在线评分系统API接口文档：[COSS4-API.md](./COSS4-API.md)

### 二、项目描述文档

Eureka 服务发现模块：[Eureka Server](./eureka-server/README.md)

Zuul 服务网关模块：[API Gateway](./coss4-api-gateway/README.md)

其它主要服务模块：[COSS4 Services](./coss4-services/README.md)

## 目录结构

## 版权说明

该项目签署了MIT授权许可，详情请参阅 [LICENSE](./LICENSE) 文件
