##  Actuator 健康检查,审计,统计和监控
接口访问地址:http://localhost:8080/actuator/

* auditevents	    显示应用暴露的审计事件 (比如认证进入、订单失败)
* info	            显示应用的基本信息
* health	        显示应用的健康状态
* metrics	        显示应用多样的度量信息
* loggers	        显示和修改配置的loggers
* logfile	        返回log file中的内容(如果logging.file或者logging.path被设置)
* httptrace	        显示HTTP足迹，最近100个HTTP request/repsponse
* env	            显示当前的环境特性
* flyway	        显示数据库迁移路径的详细信息
* liquidbase	    显示Liquibase 数据库迁移的纤细信息
* shutdown	        让你逐步关闭应用
* mappings	        显示所有的@RequestMapping路径
* scheduledtasks	显示应用中的调度任务
* threaddump	    执行一个线程dump
* heapdump	        返回一个GZip压缩的JVM堆dump


### 整合
Actuator同时还可以与外部应用监控系统整合，比如 Prometheus, Graphite, DataDog, Influx, Wavefront, New Relic等。这些系统提供了非常好的仪表盘、图标、分析和告警等功能，使得你可以通过统一的接口轻松的监控和管理你的应用。


