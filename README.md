# 冒险岛卷轴组合计算网站后端

## 模块
1. scroll-server-calculate-service
卷轴计算服务对外提供dubbo调用
2. scroll-server-web
对外提供计算接口

##使用到的技术
### 后端
1. Spring Boot；
2. Redis做集群部署；
3. RabbitMQ将卷轴全选时的长时间操作同步转异步处理；
4. MyBatis数据持久化；
5. MySQL数据库；
6. Dubbo + Zookeeper实现RPC调度和服务拆分；
7. 基于Docker容器部署；
8. Nginx负载均衡。
### 前端
1. Vue;
2. Element UI。

##使用到的算法
1. 深度优先搜索；
2. 两次剪枝（因为全选卷轴计算时计算量可达15的15次方左右，计算量较大，故要做剪枝和同步转异步）。

##网站
http://175.24.121.53:8080/mxd/

##前端
https://github.com/fubicheng208/mxd-scroll-V2-vue



