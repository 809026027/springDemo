本实例基于 http://www.sojson.com/shiro 搭建的springDemo。

## 说明
```
1、集成了redis，log4j，mybatis，mysql数据库，内嵌有shiro权限管理，scheduler定时任务。

2、前端目前只使用bootstrap,jquery,可以满足最基本开发,后续逐渐添加前端更丰富的组件。

3、项目使用了freemarker和jsp两种视图解析器，按照顺序进行适配。

4、启动项目前需要开启redis服务，修改数据库连接信息并执行init下的sql初始化脚本。

4、基于此项目可以快速搭建springMVC工程进行开发任务，缩短了搭建成本。
```

```
demo已经部署到线上，地址是http://shiro.itboy.net
登录帐号：admin，密码：sojson.com 如果密码错误，请用sojson。
```
