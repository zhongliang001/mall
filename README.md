# mall 店铺管理系统
本项目是店铺管理系统，支持管理员对店铺人员进行角色，权限的配置，以及店铺管理人员对店铺商品订单的管理
## 后端技术
技术名称|作用|官网
---|---|---
SpringCloud|微服务架构|[https://spring.io/projects/spring-cloud](https://spring.io/projects/spring-cloud)
SpringBoot|Spring 快速配置脚手架|[https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
Swagger|api文档生成工具|[https://swagger.io/tools/swagger-ui/](https://swagger.io/tools/swagger-ui/)
Apollo|配置中心|[https://ctripcorp.github.io/apollo/#/zh/README](https://ctripcorp.github.io/apollo/#/zh/README)
Mybatis|持久层框架|[https://mybatis.org/mybatis-3/zh/index.html](https://mybatis.org/mybatis-3/zh/index.html)

## 前端技术
技术名称|作用|官网
---|---|---
vue3|前端javascript框架|[https://cn.vuejs.org/](https://cn.vuejs.org/)
elemenet-plus|前端ui框架|[https://element-plus.org](https://element-plus.org)
vite|构建工具|[https://cn.vitejs.dev/](https://cn.vitejs.dev/)
rollup|打包工具|[https://www.rollupjs.com/](https://www.rollupjs.com/)

## 数据库
项目使用数据库：mysql, redis

## 项目工程
工程名|作用
---|---
mall-web|前端页面
mall-parent|统一管理后端项目依赖包版本
mall-admin|项目运行的监控系统
mall-register|注册中心
mall-gateway|网关系统
mall-uaa|鉴权系统
mall-common|公共的组件
mall-utils|工具类
mall-base|配置管理系统
mall-base-dto|配置管理系统entity，dto
mall-base-service|配置管理系统服务
mall-base-feign|配置管理系统对外提供的feign接口
mall-user|用户管理系统
mall-user-dto|用户管理系统entity，dto
mall-user-service|用户管理系统服务
mall-user-feign|用户管理系统对外提供feign接口
mall-shop|店铺管理系统
mall-shop-dto|店铺管理系统entity，dto
mall-shop-service|店铺管理系统服务
mall-shop-feign|店铺管理系统对外提供feign接口

## 项目运行
### 1. 后端打包
```shell
cd mall/mall-parent
mvn clean package -P dev
```
### 2. 后端项目启动
```shell
nohup java -jar register.jar  > /dev/null 2>&1 &
nohup java -jar mall-admin.jar > /dev/null 2>&1 &
nohup java -jar gateway.jar > /dev/null 2>&1 &
nohup java -jar uaa.jar > /dev/null 2>&1 &
nohup java -jar mall-user-service.jar  > /dev/null 2>&1 &
nohup java -jar mall-base-service.jar  > /dev/null 2>&1 &
nohup java -jar mall-shop-service.jar  > /dev/null 2>&1 &
```
### 3.前端打包
```shell
npm run build
```
前端打包后，使用nginx运行即可